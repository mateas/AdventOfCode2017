package se.sjolinder.advent21;

import java.util.*;


class Matrix<T> {
    int flopindex = 1;
    ArrayList<ArrayList<T>> rows = new ArrayList<>();

    public Matrix() {
    }


    public static Matrix<Integer> parsString(String input) {
        Matrix<Integer> metrix = new Matrix<>();
        String[] split = input.split("/");
        for (int i = 0; i < split.length; i++) {
            metrix.rows.add(new ArrayList<>());
            for (int j = 0; j < split[i].length(); j++) {
                char c = split[i].charAt(j);
                if (c == '#') {
                    metrix.rows.get(i).add(1);
                } else {
                    metrix.rows.get(i).add(0);
                }
            }
        }
        return metrix;
    }

    public Matrix<T> subMatrix(int rowNumber, int columnNumber, int size) {
        Matrix<T> matrix = new Matrix<>();
        for (int i = rowNumber * size; i < rowNumber * size + size; i++) {
            ArrayList<T> row = new ArrayList<>();
            for (int j = columnNumber * size; j < columnNumber * size + size; j++) {
                row.add(rows.get(i).get(j));
            }
            matrix.rows.add(row);
        }
        return matrix;
    }

    public Matrix<T> flip() {
        Matrix matrix = new Matrix();
        for (int i = 0; i < rows.size(); i++) {
            ArrayList<T> row = new ArrayList<>();
            for (int j = 0; j < rows.get(i).size(); j++) {
                row.add(0, rows.get(i).get(j));
            }
            matrix.rows.add(row);
        }
        return matrix;
    }

    public Matrix<T> rotate() {
        Matrix<T> matrix = new Matrix<>();
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(i).size(); j++) {
                if (i == 0)
                    matrix.rows.add(0, new ArrayList<>());

                ArrayList<T> row = matrix.rows.get(matrix.rows.size()-1 - j);
                row.add(rows.get(i).get(j));
                //row.add(rows.get((rows.get(i).size() - 1) - j).get(i));
            }
        }
        return matrix;
    }

    public Matrix<T> clone() {
        Matrix matrix = new Matrix();
        for (int i = 0; i < rows.size(); i++) {
            ArrayList<T> row = new ArrayList<>();
            for (int j = 0; j < rows.get(i).size(); j++) {
                row.add(rows.get(i).get(j));
            }
            matrix.rows.add(row);
        }
        return matrix;
    }

    public int size() {
        return rows.size();
    }

    public boolean stillFlop() {
        return flopindex % 9 != 0;
    }

    public Matrix<T> flop() {
        Matrix<T> result = this;
        for (int i = 2; i < (flopindex - 1) / 2 + 2; i++) {
            result = result.rotate();
        }
        if (flopindex % 2 == 0)
            result = result.flip();
        flopindex++;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Matrix<T> that = (Matrix<T>) obj;

        for (int i = 0; i < this.rows.size(); i++) {
            for (int j = 0; j < this.rows.get(0).size(); j++) {
                if (!this.rows.get(i).get(j).equals(that.rows.get(i).get(j)))
                    return false;
            }
        }
        return true;
    }

    public void addCells(Matrix<T> matrix) {
        for (int i = 0; i < matrix.rows.size(); i++) {
            for (int j = 0; j < matrix.rows.get(0).size(); j++) {
                if (this.rows.size() <= i) {
                    this.rows.add(new ArrayList<>());
                }
                this.rows.get(i).add(matrix.rows.get(i).get(j));
            }
        }
    }

    public void addRows(Matrix<T> matrix) {
        for (int i = 0; i < matrix.rows.size(); i++) {
            ArrayList<T> newRow = new ArrayList<>();
            this.rows.add(newRow);
            for (int j = 0; j < matrix.rows.get(0).size(); j++) {
                newRow.add(matrix.rows.get(i).get(j));
            }
        }
    }
}
