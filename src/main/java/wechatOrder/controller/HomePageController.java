package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wechatOrder.dao.HomepagePictureMapper;
import wechatOrder.po.HomepagePicture;
import wechatOrder.po.HomepagePictureExample;
import wechatOrder.po.vo.HomepagePictureVo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author JJ
 * @date 2020/2/9 - 13:25
 */
@RestController
@RequestMapping("/homepage/picture")
public class HomePageController {

    @Autowired
    private HomepagePictureMapper homepagePictureMapper;

    /**
     *
     * @param type
     * @param state
     * @return
     */
    @RequestMapping("/queryAll")
    public ResponseEntity queryAll(String type,String state){
        HomepagePictureExample homepagePictureExample = new HomepagePictureExample();
        HomepagePictureExample.Criteria criteria = homepagePictureExample.createCriteria();
        if(type!=null && !"".equals(type)){
            criteria.andTypeEqualTo(type);
        }
        if(state!=null && !"".equals(state)){
            criteria.andStateEqualTo(state);
        }
        List<HomepagePicture> homepagePictures = homepagePictureMapper.selectByExampleWithBLOBs(homepagePictureExample);
        return new ResponseEntity(homepagePictures, HttpStatus.OK);
    }

    @RequestMapping("/queryAll/page")
    public ModelAndView queryAllToPage(ModelAndView mv){
        HomepagePictureExample example = new HomepagePictureExample();
        example.setOrderByClause("state desc,type desc,create_data desc");
        List<HomepagePicture> homepagePictures = homepagePictureMapper.selectByExampleWithBLOBs(example);
        mv.addObject("homepagePictures",homepagePictures);
        mv.setViewName("advertise-list");
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/saveInBatch")
    public ResponseEntity saveInBatch(HomepagePictureVo homepagePictureVo){
        List<HomepagePicture> homepagePictures = homepagePictureVo.getHomepagePictures();
        for (HomepagePicture homepagePicture : homepagePictures) {
            if("0".equals(homepagePicture.getUrl())){
                continue;
            }
            homepagePicture.setCreateData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            homepagePictureMapper.insertSelective(homepagePicture);
        }
        return new ResponseEntity("添加成功",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/save")
    public ResponseEntity save(HomepagePicture homepagePicture){

        homepagePicture.setCreateData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int result=homepagePictureMapper.insertSelective(homepagePicture);
        if(result==1){
            return new ResponseEntity("添加成功",HttpStatus.OK);
        }else{
            return new ResponseEntity("添加失败",HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/delete")
    public ResponseEntity delete(Integer[] ids){
        for (Integer id : ids) {
            homepagePictureMapper.deleteByPrimaryKey(id);
        }
        return new ResponseEntity("删除成功",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/updateInBatch")
    public ResponseEntity updateInBatch(HomepagePictureVo homepagePictureVo){
        List<HomepagePicture> homepagePictures = homepagePictureVo.getHomepagePictures();
        for (HomepagePicture homepagePicture : homepagePictures) {
            if(homepagePicture.getId()==null){
                return new ResponseEntity("homepagePictureId为空",HttpStatus.BAD_REQUEST);
            }
        }

        for (HomepagePicture homepagePicture : homepagePictures) {
            homepagePictureMapper.updateByPrimaryKeySelective(homepagePicture);
        }
        return new ResponseEntity("修改成功",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/update")
    public ResponseEntity update(HomepagePicture homepagePicture){
        if(homepagePicture.getId()==null){
            return new ResponseEntity("homepagePictureId不能为空",HttpStatus.PRECONDITION_REQUIRED);
        }
        homepagePictureMapper.updateByPrimaryKeySelective(homepagePicture);
        return new ResponseEntity("修改成功",HttpStatus.OK);
    }
}
