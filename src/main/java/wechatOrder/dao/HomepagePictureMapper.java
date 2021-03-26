package wechatOrder.dao;

import org.apache.ibatis.annotations.Param;
import wechatOrder.po.HomepagePicture;
import wechatOrder.po.HomepagePictureExample;

import java.util.List;

public interface HomepagePictureMapper {
    int countByExample(HomepagePictureExample example);

    int deleteByExample(HomepagePictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomepagePicture record);

    int insertSelective(HomepagePicture record);

    List<HomepagePicture> selectByExampleWithBLOBs(HomepagePictureExample example);

    List<HomepagePicture> selectByExample(HomepagePictureExample example);

    HomepagePicture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomepagePicture record, @Param("example") HomepagePictureExample example);

    int updateByExampleWithBLOBs(@Param("record") HomepagePicture record, @Param("example") HomepagePictureExample example);

    int updateByExample(@Param("record") HomepagePicture record, @Param("example") HomepagePictureExample example);

    int updateByPrimaryKeySelective(HomepagePicture record);

    int updateByPrimaryKeyWithBLOBs(HomepagePicture record);

    int updateByPrimaryKey(HomepagePicture record);
}