package com.example.demojpa.Repository;

import com.example.demojpa.data.CandyData;
import dao.CandyDAO;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CandyDAOImpl implements CandyDAO {

  //we can avoid some typo-based errors by using string constants
  private static final String CANDY_ID = "candyId";
  private static final String DELIVERY_ID = "deliveryId";

  @Autowired
  NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private static final String  SELECT_ALL_CANDY = "SELECT * FROM CANDY";

  private static final RowMapper<CandyData> candyDataRowMapper = new BeanPropertyRowMapper<>(CandyData.class);

  private static final String INSERT_DELIVERY =
      "INSERT INTO candy_delivery (candy_id, delivery_id) " +
          "VALUES (:" + CANDY_ID + ", :" + DELIVERY_ID + ")";

  private static final String FIND_CANDY_BY_DELIVERY =
      "SELECT c.* FROM candy_delivery AS cd " +
          "JOIN candy AS c on c.id = cd.candy_id " +
          "WHERE cd.delivery_id = :" + DELIVERY_ID;

  @Override
  public List<CandyData> list() {
    return namedParameterJdbcTemplate.query(
        SELECT_ALL_CANDY,candyDataRowMapper);
  }

  @Override
  public void addToDelivery(Long candyId, Long deliveryid) {
    KeyHolder key = new GeneratedKeyHolder();
    namedParameterJdbcTemplate.update(INSERT_DELIVERY,
        new MapSqlParameterSource()
        .addValue(CANDY_ID, candyId)
        .addValue(DELIVERY_ID, deliveryid),
        key
        );
  }

  @Override
  public List<CandyData> findByDelivery(Long deliveryId) {
    return namedParameterJdbcTemplate.query(
        FIND_CANDY_BY_DELIVERY,
        new MapSqlParameterSource(DELIVERY_ID, deliveryId),
        candyDataRowMapper
    );
  }

    public List<CandyData> findByAllDelivery(Long deliveryId) {
      return namedParameterJdbcTemplate.query(FIND_CANDY_BY_DELIVERY,
          new MapSqlParameterSource(DELIVERY_ID, deliveryId),
          candyDataRowMapper);
    }

}

