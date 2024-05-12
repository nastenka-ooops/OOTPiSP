package com.example.lapa12.visitor;

import com.example.lapa12.heros.*;

public interface Visitor {
    void visitHilichurl(Hilichurl hilichurl);
    void visitHilichurlFighter(HilichurlFighter hilichurlFighter);
    void visitHilichurlGrenadier(HilichurlGrenadier hilichurlGrenadier);
    void visitHilichurlGuard(HilichurlGuard hilichurlGuard);
    void visitHilichurlShooter(HilichurlShooter hilichurlShooter);
    void visitLawachurl(Lawachurl lawachurl);
    void visitMitachurl(Mitachurl mitachurl);
}
