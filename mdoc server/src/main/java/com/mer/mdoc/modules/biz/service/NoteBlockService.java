package com.mer.mdoc.modules.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mer.mdoc.modules.biz.entity.NoteBlock;
import com.mer.mdoc.modules.biz.mapper.NoteBlockMapper;
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
public class NoteBlockService {

    @Autowired
    private NoteBlockMapper noteBlockMapper;

    public List<NoteBlock> get(String uId) {
        QueryWrapper<NoteBlock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id", uId);
        return noteBlockMapper.selectList(queryWrapper);
    }

    public boolean add(NoteBlock noteBlock) {
        return noteBlockMapper.insert(noteBlock) == 1;
    }

    public boolean update(NoteBlock noteBlock) {
        return noteBlockMapper.updateById(noteBlock) == 1;
    }

    public boolean delete(String id) {
        return noteBlockMapper.deleteById(id) == 1;
    }

}
