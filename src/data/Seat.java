/*
 * The MIT License
 *
 * Copyright 2016 Miguel Angel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package data;

/**
 *
 * @author Miguel Angel
 */
public class Seat {

    private Player player;
    private boolean folded;
    private boolean allin;
    private boolean check;

    public void seatPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void unSeatPlayer() {
        this.player = null;
    }

    /**
     * @return the folded
     */
    public boolean isFolded() {
        return folded;
    }

    /**
     * @param folded the folded to set
     */
    public void setFolded(boolean folded) {
        this.folded = folded;
    }

    /**
     * @return the allin
     */
    public boolean isAllin() {
        return allin;
    }

    /**
     * @param allin the allin to set
     */
    public void setAllin(boolean allin) {
        this.allin = allin;
    }

    /**
     * @return the check
     */
    public boolean isCheck() {
        return check;
    }

    public boolean isOccupied() {
        return !(player == null);
    }

    /**
     * @param check the check to set
     */
    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return player.toString();
    }

}
