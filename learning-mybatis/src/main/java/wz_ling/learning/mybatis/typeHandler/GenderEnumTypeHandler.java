package wz_ling.learning.mybatis.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(GenderEnum.class)
public class GenderEnumTypeHandler extends BaseTypeHandler<GenderEnum> {
    @Override
    public GenderEnum getResult(ResultSet rs, String columnName) throws SQLException {
        return getNullableResult(rs, columnName);
    }

    @Override
    public GenderEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getNullableResult(rs, columnIndex);
    }

    @Override
    public GenderEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getNullableResult(cs, columnIndex);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, GenderEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(1, parameter.getCode());
    }

    @Override
    public GenderEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return GenderEnum.getGenderEnum(rs.getInt(columnName));
    }

    @Override
    public GenderEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return GenderEnum.getGenderEnum(rs.getInt(columnIndex));
    }

    @Override
    public GenderEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return GenderEnum.getGenderEnum(cs.getInt(columnIndex));
    }
}
