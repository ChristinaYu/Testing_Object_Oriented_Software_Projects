package edu.unm.cs583;
import java.util.Vector;
import java.awt.Point;

abstract class CompositePiece extends StationaryPiece {
    protected Vector _list;

    public CompositePiece(PlayingField fieldPtr, Point atPoint) {
        super(fieldPtr, atPoint);
        _list = new Vector(10);
    }

    public void finalize() {
        _list.removeAllElements();
    }

    public void collideWithPaddle(Paddle aPaddle, Point atPoint) {
        ArcadeGamePiece itemPtr;
        if (!_list.isEmpty()) {
            for (int i = 0; i < _list.size(); i++) {
                itemPtr = (ArcadeGamePiece)_list.elementAt(i);
                itemPtr.collideWithPaddle(aPaddle, atPoint);
            }
        }
    }

    public void collideWithPuck(Puck aPuck, Point atPoint) {
        ArcadeGamePiece itemPtr;
        if (!_list.isEmpty()) {
            for (int i = 0; i < _list.size(); i++) {
                itemPtr = (ArcadeGamePiece)_list.elementAt(i);
                itemPtr.collideWithPuck(aPuck, atPoint);
            }
        }
    }

    public boolean isEmpty() {
        if (_list.isEmpty())
            return true;
        else
            return false;
    }
}
