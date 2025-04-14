package com.guld.sciq.debate.entity;

public enum DebateStatus {
    PENDING,    // 관리자 승인 대기
    OPEN,       // 토론 진행 중
    CLOSED,     // 토론 종료
    REJECTED    // 관리자 거절
} 