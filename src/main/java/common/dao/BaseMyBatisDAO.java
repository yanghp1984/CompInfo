package common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import common.bean.Paging;

/**
 * 通过 MyBatis 操作数据库的通用DAO接口的实现
 * 
 * @author bianj
 * @version 1.0.0 2017-07-10
 */
@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class BaseMyBatisDAO implements IBaseMyBatisDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(String sqlId) {
		return sqlSession.insert(sqlId);
	}

	@Override
	public int insert(String sqlId, Object parameter) {
		return sqlSession.insert(sqlId, parameter);
	}

	@Override
	public int update(String sqlId) {
		return sqlSession.update(sqlId);
	}

	@Override
	public int update(String sqlId, Object parameter) {
		return sqlSession.update(sqlId, parameter);
	}

	@Override
	public int delete(String sqlId) {
		return sqlSession.delete(sqlId);
	}

	@Override
	public int delete(String sqlId, Object parameter) {
		return sqlSession.delete(sqlId, parameter);
	}

	@Override
	public <T> T findUnique(String sqlId) {
		return sqlSession.selectOne(sqlId);
	}

	@Override
	public <T> T findUnique(String sqlId, Object parameter) {
		return sqlSession.selectOne(sqlId, parameter);
	}

	@Override
	public <T> List<T> findForList(String sqlId) {
		return sqlSession.selectList(sqlId);
	}

	@Override
	public <T> List<T> findForList(String sqlId, Object parameter) {
		return sqlSession.selectList(sqlId, parameter);
	}

	@Override
	public Paging findForPageHelper(String dataSqlId, Paging paging) {
		Map<String, Object> parameters = (Map<String, Object>) paging.getParameters();
		if (parameters == null) {
			parameters = new HashMap<String, Object>();
		}
		paging.clearRows();
		
		// 设置参数
		parameters.put("sort", paging.getSort());
		parameters.put("order", paging.getOrder());
		
		// Mybatis - 通用分页拦截器
		PageHelper.startPage(paging.getPage(), paging.getPageSize());
		// 包装 Page 对象
		PageInfo pageInfo = new PageInfo(this.findForList(dataSqlId, parameters));
		// 设置当前页
		paging.setPage(pageInfo.getPageNum());
		// 设置总记录数
		paging.setTotal(pageInfo.getTotal());
		// 设置结果数据集
		paging.setRows(pageInfo.getList());
		
		return paging;
	}

}
