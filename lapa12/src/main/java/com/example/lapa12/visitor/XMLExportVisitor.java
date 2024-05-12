package com.example.lapa12.visitor;

import com.example.lapa12.heros.*;

public class XMLExportVisitor implements Visitor {

    StringBuilder XMLString;

    public XMLExportVisitor() {
        XMLString = new StringBuilder();
    }

    public String export(Hilichurls hilichurls) {
        XMLString.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n");
        for (Hilichurl hilichurl :
                hilichurls.hilichurls) {
            hilichurl.accept(this);
            XMLString.append("\n");
        }
        return XMLString.toString();
    }

    @Override
    public void visitHilichurl(Hilichurl hilichurl) {
        String temp = "<Hilichurl>" + "\n" +
                "    <level>" + hilichurl.getLevel() + "</level>" + "\n" +
                "    <XP>" + hilichurl.getXP() + "</XP>" + "\n" +
                "    <maxXP>" + hilichurl.getMaxXP() + "</maxXP>" + "\n" +
                "    <name>" + hilichurl.getName() + "</name>" + "\n" +
                "    <imagePath>" + hilichurl.getImagePath() + "</imagePath>" + "\n" +
                "    <x>" + hilichurl.getX() + "</x>" + "\n" +
                "    <y>" + hilichurl.getY() + "</y>" + "\n" +
                "</Hilichurl>";
        XMLString.append(temp);
    }

    @Override
    public void visitHilichurlFighter(HilichurlFighter hilichurlFighter) {
        String temp = "<HilichurlFighter>" + "\n" +
                "    <level>" + hilichurlFighter.getLevel() + "</level>" + "\n" +
                "    <XP>" + hilichurlFighter.getXP() + "</XP>" + "\n" +
                "    <maxXP>" + hilichurlFighter.getMaxXP() + "</maxXP>" + "\n" +
                "    <name>" + hilichurlFighter.getName() + "</name>" + "\n" +
                "    <imagePath>" + hilichurlFighter.getImagePath() + "</imagePath>" + "\n" +
                "    <x>" + hilichurlFighter.getX() + "</x>" + "\n" +
                "    <y>" + hilichurlFighter.getY() + "</y>" + "\n" +
                "    <club>" + hilichurlFighter.getClub() + "</club>" + "\n" +
                "</HilichurlFighter>";
        XMLString.append(temp);
    }

    @Override
    public void visitHilichurlGrenadier(HilichurlGrenadier hilichurlGrenadier) {
        String temp = "<HilichurlGrenadier>" + "\n" +
                "    <level>" + hilichurlGrenadier.getLevel() + "</level>" + "\n" +
                "    <XP>" + hilichurlGrenadier.getXP() + "</XP>" + "\n" +
                "    <maxXP>" + hilichurlGrenadier.getMaxXP() + "</maxXP>" + "\n" +
                "    <name>" + hilichurlGrenadier.getName() + "</name>" + "\n" +
                "    <imagePath>" + hilichurlGrenadier.getImagePath() + "</imagePath>" + "\n" +
                "    <x>" + hilichurlGrenadier.getX() + "</x>" + "\n" +
                "    <y>" + hilichurlGrenadier.getY() + "</y>" + "\n" +
                "    <slime>" + hilichurlGrenadier.getSlime() + "</slime>" + "\n" +
                "</HilichurlGrenadier>";
        XMLString.append(temp);
    }

    @Override
    public void visitHilichurlGuard(HilichurlGuard hilichurlGuard) {
        String temp = "<HilichurlGuard>" + "\n" +
                "    <level>" + hilichurlGuard.getLevel() + "</level>" + "\n" +
                "    <XP>" + hilichurlGuard.getXP() + "</XP>" + "\n" +
                "    <maxXP>" + hilichurlGuard.getMaxXP() + "</maxXP>" + "\n" +
                "    <name>" + hilichurlGuard.getName() + "</name>" + "\n" +
                "    <imagePath>" + hilichurlGuard.getImagePath() + "</imagePath>" + "\n" +
                "    <x>" + hilichurlGuard.getX() + "</x>" + "\n" +
                "    <y>" + hilichurlGuard.getY() + "</y>" + "\n" +
                "    <club>" + hilichurlGuard.getClub() + "</club>" + "\n" +
                "    <shield>" + hilichurlGuard.getShield() + "</shield>" + "\n" +
                "</HilichurlGuard>";
        XMLString.append(temp);
    }

    @Override
    public void visitHilichurlShooter(HilichurlShooter hilichurlShooter) {
        String temp = "<HilichurlShooter>" + "\n" +
                "    <level>" + hilichurlShooter.getLevel() + "</level>" + "\n" +
                "    <XP>" + hilichurlShooter.getXP() + "</XP>" + "\n" +
                "    <maxXP>" + hilichurlShooter.getMaxXP() + "</maxXP>" + "\n" +
                "    <name>" + hilichurlShooter.getName() + "</name>" + "\n" +
                "    <imagePath>" + hilichurlShooter.getImagePath() + "</imagePath>" + "\n" +
                "    <x>" + hilichurlShooter.getX() + "</x>" + "\n" +
                "    <y>" + hilichurlShooter.getY() + "</y>" + "\n" +
                "    <club>" + hilichurlShooter.getCrossbow() + "</club>" + "\n" +
                "</HilichurlFighter>";
        XMLString.append(temp);
    }

    @Override
    public void visitLawachurl(Lawachurl lawachurl) {
        String temp = "<Lawachurl>" + "\n" +
                "    <level>" + lawachurl.getLevel() + "</level>" + "\n" +
                "    <XP>" + lawachurl.getXP() + "</XP>" + "\n" +
                "    <maxXP>" + lawachurl.getMaxXP() + "</maxXP>" + "\n" +
                "    <name>" + lawachurl.getName() + "</name>" + "\n" +
                "    <imagePath>" + lawachurl.getImagePath() + "</imagePath>" + "\n" +
                "    <x>" + lawachurl.getX() + "</x>" + "\n" +
                "    <y>" + lawachurl.getY() + "</y>" + "\n" +
                "    <axe>" + lawachurl.getAxe() + "</axe>" + "\n" +
                "    <shield>" + lawachurl.getShield() + "</shield>" + "\n" +
                "    <shell>" + lawachurl.getShell() + "</shell>" + "\n" +
                "</Lawachurl>";
        XMLString.append(temp);

    }

    @Override
    public void visitMitachurl(Mitachurl mitachurl) {
        String temp = "<Mitachurl>" + "\n" +
                "    <level>" + mitachurl.getLevel() + "</level>" + "\n" +
                "    <XP>" + mitachurl.getXP() + "</XP>" + "\n" +
                "    <maxXP>" + mitachurl.getMaxXP() + "</maxXP>" + "\n" +
                "    <name>" + mitachurl.getName() + "</name>" + "\n" +
                "    <imagePath>" + mitachurl.getImagePath() + "</imagePath>" + "\n" +
                "    <x>" + mitachurl.getX() + "</x>" + "\n" +
                "    <y>" + mitachurl.getY() + "</y>" + "\n" +
                "    <axe>" + mitachurl.getAxe() + "</axe>" + "\n" +
                "    <shield>" + mitachurl.getShield() + "</shield>" + "\n" +
                "</Mitachurl>";
        XMLString.append(temp);
    }
}
