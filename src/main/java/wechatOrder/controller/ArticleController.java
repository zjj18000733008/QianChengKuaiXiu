package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wechatOrder.dao.ArticleMapper;
import wechatOrder.dao.ArticleTypeMapper;
import wechatOrder.po.Article;
import wechatOrder.po.ArticleExample;
import wechatOrder.po.ArticleType;
import wechatOrder.po.ArticleTypeExample;

import java.util.List;

/**
 * @author JJ
 * @date 2020/2/25 - 15:14
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    @RequestMapping("/save")
    public ResponseEntity save(Article article) {
        int i = articleMapper.insertSelective(article);
        if (i == 1) {
            return new ResponseEntity("添加成功", HttpStatus.OK);
        } else {
            return new ResponseEntity("添加失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/update")
    public ResponseEntity update(Article article){
        if(article.getId()==null){
            return new ResponseEntity("文章id不能为空",HttpStatus.PRECONDITION_REQUIRED);
        }
        int i=articleMapper.updateByPrimaryKeyWithBLOBs(article);
        if(i==1){
            return new ResponseEntity("修改成功",HttpStatus.OK);
        }else{
            return new ResponseEntity("修改失败",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/get")
    public ResponseEntity get(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return new ResponseEntity(article, HttpStatus.OK);
    }

    @RequestMapping("/queryAll")
    public ResponseEntity queryAll() {
        List<Article> articles = articleMapper.selectByExampleWithBLOBs(new ArticleExample());
        return new ResponseEntity(articles, HttpStatus.OK);
    }

    @RequestMapping("/queryAll/page")
    public ModelAndView queryAllToPage(ModelAndView mv) {
        ArticleExample articleExample = new ArticleExample();
        List<Article> articles = articleMapper.selectByExampleWithBLOBs(articleExample);
        mv.addObject("articles",articles);
        mv.setViewName("article-list");
        return mv;
    }

    @RequestMapping("/getById")
    public ModelAndView getById(ModelAndView mv,Integer id){

        Article article = articleMapper.selectByPrimaryKey(id);
        mv.setViewName("article-show");
        mv.addObject("article",article);

        return mv;
    }

    @RequestMapping("/delete")
    public ResponseEntity delete(Integer id) {
        int i = articleMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return new ResponseEntity("删除成功", HttpStatus.OK);
        } else {
            return new ResponseEntity("删除失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/deleteInBatch")
    public ResponseEntity deleteInBatch(Integer[] ids){
        return null;
    }

    @RequestMapping("/type")
    public ResponseEntity type() {
        List<ArticleType> articleTypes = articleTypeMapper.selectByExample(new ArticleTypeExample());
        return new ResponseEntity(articleTypes, HttpStatus.OK);
    }


}
