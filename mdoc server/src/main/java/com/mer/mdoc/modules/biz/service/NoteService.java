package com.mer.mdoc.modules.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mer.mdoc.modules.biz.entity.Note;
import com.mer.mdoc.modules.biz.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxc
 * @date 2021-01-18 9:10
 * @version: 3.0
 * @description TODO
 */
@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    public List<Note> get(String blockId) {
        QueryWrapper<Note> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("BLOCK_ID", blockId);
        return noteMapper.selectList(queryWrapper);
    }

    public boolean add(Note note) {
        return noteMapper.insert(note) == 1;
    }

    public boolean update(Note note) {
        return noteMapper.insert(note) == 1;
    }

}
