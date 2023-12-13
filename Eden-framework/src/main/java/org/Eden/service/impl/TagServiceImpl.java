package org.Eden.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.Eden.domain.entity.Tag;
import org.Eden.mapper.TagMapper;
import org.Eden.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}