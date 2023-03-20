package com.example.service.impl;

import com.example.entity.Memo;
import com.example.mapper.MemoMapper;
import com.example.service.MemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Flat-White
 * @since 2021-12-04
 */
@Service
public class MemoServiceImpl extends ServiceImpl<MemoMapper, Memo> implements MemoService {

}
