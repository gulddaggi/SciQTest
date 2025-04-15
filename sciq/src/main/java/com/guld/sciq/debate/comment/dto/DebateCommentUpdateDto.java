package com.guld.sciq.debate.dto;

import com.guld.sciq.debate.entity.DebateStance;

public record DebateCommentUpdateDto(String content, DebateStance stance	) {
}