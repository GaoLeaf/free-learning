package cn.free.spring.mybatis.demo.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author gaowenjin
 * @date 2020/11/12
 * @description:
 */
public class MoneyTypeHandler extends BaseTypeHandler<Money> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Money parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter.getAmountMajorLong());
    }

    @Override
    public Money getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parse(rs.getLong(columnName));
    }

    @Override
    public Money getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parse(rs.getLong(columnIndex));
    }

    @Override
    public Money getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parse(cs.getLong(columnIndex));
    }

    private Money parse(Long value) {
        return Money.of(CurrencyUnit.of("CNY"), value / 100.0);
    }
}
