
package io.github.galaipa.bb;



public class Winners implements Comparable<Winners> {
    Team team;
    int score;

    public Winners(Team name, int score){
        this.team = name;
        this.score = score;
    }

    public int compareTo(Winners user){
        return user.getScore() - this.getScore();
    }

    public Team getName(){
        return team;
    }

    public int getScore(){
        return score;
    }

    public String toString(){
        return "Name: " + team + " Score: " + score;
    }
}
