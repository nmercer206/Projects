package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    BigDecimal getTransferAmountById (int transferId);

    int getFromAccountById (int transferId);

    int getToAccountById (int transferId);

    String getTransferTypeById (int transferId);

    String getTransferStatusById (int transferId);

    Transfer createTransfer (Transfer transfer);

    Transfer[] transferList();





}
