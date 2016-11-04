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
package ui;

import data.Table;

/**
 *
 * @author Miguel Angel
 */
public interface UI {

    public String askMsg(String question) throws Exception;

    public int askInt(String question) throws Exception;

    public int AskMenuOption() throws Exception;

    public void printWelcome();

    public void printError(Exception ex);

    public void printHelp();

    public void printCommands();

    public void printHands();

    public void printMainMenu(Table table);

    public void printRoundMenu();

    public void printExit();

    public void printInfo();

    public void printRoundStandings(Table table, int winners);

    public void printStandings(Table table);

    public void printUser(Table table);

    public void printMsg(String msg);

}
