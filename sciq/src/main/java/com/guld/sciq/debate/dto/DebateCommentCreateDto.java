package com.guld.sciq.debate.dto;

import com.guld.sciq.debate.entity.DebateStance;

public record DebateCommentCreateDto(
    String content,
    DebateStance stance,
    String userNickName
) {} 