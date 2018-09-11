package speedchina.addresselement.dao;

import speedchina.addresselement.entity.StandardAddress;
import speedchina.addresselement.tools.Constant;

import java.util.List;
import java.util.Map;

/**
 * @author 11852
 */
public class ConfigDaoProvider {


    public String insertStandardAddressList(Map map) {
        List<StandardAddress> standardAddressList =  (List<StandardAddress>)map.get("list");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into sp_standard_address_new ");
        stringBuilder.append("(id,standard_address_name,standard_address_path,standard_address_type,administrative_region_id" +
                ",organization_id,city_id,county_id,town_id,community_id,village_community_id,street_id,housing_estate_id," +
                "natural_village_id,group_id,house_number_id,building_id,unit_id,room_id,location,status) ");
        stringBuilder.append("values ");
        for (int i = 0; i < standardAddressList.size(); i++) {
            stringBuilder.append("(");
            stringBuilder.append("\""+standardAddressList.get(i).getId()+"\",");
            stringBuilder.append("'"+standardAddressList.get(i).getStandardAddressPath()+"',");
            stringBuilder.append("'"+standardAddressList.get(i).getStandardAddressName()+"',");
            stringBuilder.append("'"+standardAddressList.get(i).getLocation()+"',");
            stringBuilder.append("'"+Constant.USEING_STATUS +"',");
            stringBuilder.append(")");
            if (i < standardAddressList.size() - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
