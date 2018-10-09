package com.smh.dao;

import com.smh.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by hui10.yang on 18/9/30.
 */
@Repository
public interface UserDAO {
    void insert(User user);

    void delete(User user);
}
