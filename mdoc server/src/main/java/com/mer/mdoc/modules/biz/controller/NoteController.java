package com.mer.mdoc.modules.biz.controller;

import com.mer.mdoc.core.vo.Result;
import com.mer.mdoc.modules.biz.entity.Note;
import com.mer.mdoc.modules.biz.service.NoteService;
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
@RequestMapping("/note/")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @RequestMapping("get")
    public Result<List<Note>> get(String blockId) {
        return Result.data(noteService.get(blockId));
    }

    @RequestMapping("add")
    public Result<Object> add(Note note) {
        if (noteService.add(note)) {
            return Result.data(note);
        } else {
            return Result.error("保存失败");
        }
    }

    @RequestMapping("update")
    public Result<Object> update(Note note) {
        if (noteService.update(note)) {
            return Result.ok();
        } else {
            return Result.error("更新失败");
        }
    }

}
