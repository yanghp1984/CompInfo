package common.dao;

import java.util.List;

import common.bean.Paging;

/**
 * 通过 MyBatis 操作数据库的通用DAO
 * 
 * @author bianj
 * @version 1.0.0 2017-07-10
 */
public interface IBaseMyBatisDAO {
	/**
	 * 根据 SQL 语句插入数据
	 * 
	 * @param sqlId
	 *            SQL的ID
	 * @return 影响的行数
	 */
	int insert(String sqlId);

	/**
	 * 根据参数插入数据
	 * 
	 * @param sqlId
	 *            SQL的ID
	 * @param parameter
	 *            参数
	 * @return 影响的行数
	 */
	int insert(String sqlId, Object parameter);

	/**
	 * 根据 SQL 语句修改数据
	 * 
	 * @param sqlId
	 *            SQL的ID
	 * @return 影响的行数
	 */
	int update(String sqlId);

	/**
	 * 根据参数修改数据
	 * 
	 * @param sqlId
	 *            SQL的ID
	 * @param parameter
	 *            参数
	 * @return 影响的行数
	 */
	int update(String sqlId, Object parameter);

	/**
	 * 根据 SQL 语句删除数据
	 * 
	 * @param sqlId
	 *            SQL的ID
	 * @return 影响的行数
	 */
	int delete(String sqlId);

	/**
	 * 根据参数删除数据
	 * 
	 * @param sqlId
	 *            SQL的ID
	 * @param parameter
	 *            参数
	 * @return 影响的行数
	 */
	int delete(String sqlId, Object parameter);

	/**
	 * 根据 SQL 语句查询唯一的一行数据
	 * 
	 * @param sqlId
	 *            SQL的ID
	 * @return 唯一的一行数据
	 */
	<T> T findUnique(String sqlId);

	/**
	 * 根据参数查询唯一的一行数据
	 * 
	 * @param sqlId
	 *            SQL的ID
	 * @param parameter
	 *            参数
	 * @return 唯一的一行数据
	 */
	<T> T findUnique(String sqlId, Object parameter);

	/**
	 * 根据 SQL 语句查询数据列表
	 * 
	 * @param sqlId
	 *            SQL的ID
	 * @return 数据列表
	 */
	<T> List<T> findForList(String sqlId);

	/**
	 * 根据参数查询数据列表
	 * 
	 * @param sqlId
	 *            SQL的ID
	 * @param parameter
	 *            参数
	 * @return 数据列表
	 */
	<T> List<T> findForList(String sqlId, Object parameter);

	/**
	 * 根据分页参数和查询参数，查询数据库中的分页数据列表（基于Mybatis-PageHelper分页插件）
	 * 
	 * @param dataSqlId
	 *            查询数据的SQL的ID
	 * @param paging
	 *            分页参数
	 * @return 分页数据
	 */
	Paging findForPageHelper(String dataSqlId, Paging paging);
}
