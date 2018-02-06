package common.dao;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用于各模块 DAO 或者 Service 继承的基础DAO
 * 
 * @author bianj
 * @version 1.0.0 2017-07-10
 */
public class BaseDAO {
	/** 通过 MyBatis 操作数据库的通用 DAO */
	@Autowired
	protected IBaseMyBatisDAO baseMyBatisDAO;
}
