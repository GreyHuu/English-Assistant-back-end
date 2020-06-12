package com.se1722.englishassistant.service;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 功能接口 集成此接口 用于添加基本的sql操作
 * @param <T>
 */
public interface PublicDBInterface<T> extends Mapper<T>, MySqlMapper<T> { }
