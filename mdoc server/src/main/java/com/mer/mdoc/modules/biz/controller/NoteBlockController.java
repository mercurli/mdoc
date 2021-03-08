package com.mer.mdoc.modules.biz.controller;

import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.biz.entity.NoteBlock;
import com.mer.mdoc.modules.biz.service.NoteBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wxc
 * @date 2021-01-18 9:10
 * @version: 3.0
 * @description TODO
 */
@RestController
@RequestMapping("/note/block/")
public class NoteBlockController {

    @Autowired
    private NoteBlockService noteBlockService;

    @RequestMapping("get")
    public Result<List<NoteBlock>> get(String uId) {
        return Result.data(noteBlockService.get(uId));
    }

    @RequestMapping("add")
    public Result<Object> add(NoteBlock noteBlock) {
        if (noteBlockService.add(noteBlock)) {
            return Result.ok();
        } else {
            return Result.error("保存失败");
        }
    }

    @RequestMapping("update")
    public Result<Object> update(NoteBlock noteBlock) {
        if (noteBlockService.update(noteBlock)) {
            return Result.ok();
        } else {
            return Result.error("更新失败");
        }
    }

    @RequestMapping("del")
    public Result<Object> delete(String id) {
        if (noteBlockService.delete(id)) {
            return Result.ok();
        } else {
            return Result.error("删除失败");
        }
    }

}
