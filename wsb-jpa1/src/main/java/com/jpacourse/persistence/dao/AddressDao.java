package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.AddressEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends Dao<AddressEntity, Long>
{

}
