package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.dto.EditDTO;
import com.example.entity.Memo;
import com.example.service.impl.MemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Flat-White
 * @since 2021-12-04
 */
@RestController
@RequestMapping("/memo")
public class MemoController {

    @Autowired
    MemoServiceImpl memoService;

    @GetMapping("/list/{id}/{currentPage}")
    public Result list(@PathVariable(value = "id") Integer userId,
                       @PathVariable(value = "currentPage") Integer currentPage){
        Page<Memo> page = new Page<>(currentPage, 5);
        QueryWrapper<Memo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).orderByDesc("update_time");
        Page<Memo> pageData = memoService.page(page, wrapper);

        return Result.success(pageData);
    }

//    @PostMapping("/list/")
//    public Result list(@RequestBody ListDTO listDTO){
//        Page<Memo> page = new Page<>(listDTO.getCurrentPage(), 5);
//        QueryWrapper<Memo> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_id", listDTO.getId()).orderByDesc("create_time");
//        Page<Memo> pageData = memoService.page(page, wrapper);
//
//        return Result.success(pageData);
//    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable(value = "id") Long id){
        Memo memo = memoService.getById(id);
        if (memo != null){
            return Result.success(memo);
        }else {
            return Result.fail("该备忘录已被删除");
        }
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody EditDTO editDTO){
        if (editDTO.getMemoId() != null){
            Memo memo = memoService.getById(editDTO.getMemoId());
            if (memo.getUserId().equals(editDTO.getUserId())){
                UpdateWrapper<Memo> wrapper = new UpdateWrapper<>();
                wrapper.eq("memo_id", editDTO.getMemoId());
                wrapper.set("title", editDTO.getTitle());
                wrapper.set("description", editDTO.getDescription());
                wrapper.set("content", editDTO.getContent());
//                MetaObjectHandler提供的默认方法的策略均为:
//                如果属性有值则不覆盖,如果填充值为null则不填充
//                ！有值则不填充 所以save方法需要传入一个updateTime为null的对象
                memoService.saveOrUpdate(new Memo(), wrapper);
                return Result.success("修改成功");
            }else {
                return Result.fail("只能编辑自己的备忘录");
            }
        }else {
            Memo memo = new Memo();
            memo.setUserId(editDTO.getUserId());
            memo.setTitle(editDTO.getTitle());
            memo.setDescription(editDTO.getDescription());
            memo.setContent(editDTO.getContent());
            if (memoService.save(memo)){
                return Result.success("新增成功");
            }else {
                return Result.fail("Error");
            }
        }
    }

    @GetMapping("/modify/{id}")
    public Result modify(@PathVariable Integer id){
        Memo memo = memoService.getById(id);
        if(memo != null){
            if (memo.getStatus() == 0){
                UpdateWrapper<Memo> wrapper = new UpdateWrapper<>();
                wrapper.eq("memo_id", id);
                wrapper.set("status", 1);
                memoService.update(new Memo(), wrapper);
                return Result.success("修改成功");
            }else {
                UpdateWrapper<Memo> wrapper = new UpdateWrapper<>();
                wrapper.eq("memo_id", id);
                wrapper.set("status", 0);
                memoService.update(new Memo(), wrapper);
                memoService.update(wrapper);
                return Result.success("修改成功");
            }
        }else {
            return Result.fail("备忘录不存在");
        }
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        Memo memo = memoService.getById(id);
        if (memo != null){
            memoService.removeById(id);
            return Result.success("删除成功");
        }else {
            return Result.fail("备忘录不存在");
        }
    }
}
