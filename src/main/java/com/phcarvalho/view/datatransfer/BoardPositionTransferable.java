package com.phcarvalho.view.datatransfer;

import com.phcarvalho.model.BoardPositionModel;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class BoardPositionTransferable implements Transferable {

    private static final DataFlavor DATA_FLAVOR_ARRAY[] = { new DataFlavor(BoardPositionModel.class, "BoardPosition") };

    private BoardPositionModel boardPositionModel;

    public BoardPositionTransferable(BoardPositionModel boardPositionModel) {
        this.boardPositionModel = boardPositionModel;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return DATA_FLAVOR_ARRAY;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return DATA_FLAVOR_ARRAY[0].equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) {

        if (isDataFlavorSupported(flavor))
            return boardPositionModel;

        throw new RuntimeException("Error in the transfer data getting! Flavor: " + flavor);
    }
}
