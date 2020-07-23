package com.project.back.PostManage;

import com.project.back.UserManage.User;

public class Score extends Action {
    private boolean up;


    private Score(User user) {
        super(user);
    }
    //up means if it's false score in -1 and if it's true score is +1
    public static Score scoreUp(User user){
        Score score = new Score(user);
        score.up = true;
        return score;

    }
    public static Score scoreDown(User user){
        Score score = new Score(user);
        score.up = false;
        return score;
    }

    public boolean isUp() {
        return up;
    }
}
