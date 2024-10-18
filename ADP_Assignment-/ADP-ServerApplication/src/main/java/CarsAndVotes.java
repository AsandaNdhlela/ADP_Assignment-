/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class CarsAndVotes {
    
    private String carName;
    private int vote;

    public CarsAndVotes(String carName, int vote) {
        this.carName = carName;
        this.vote = vote;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "CarsAndVotes{" + "carName=" + carName + ", vote=" + vote + '}';
    }
}
