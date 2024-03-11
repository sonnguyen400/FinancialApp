package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Transfer;
import com.sonnguyen.individual.nhs.Repository.IRepository.ITransferRepository;

public class TransferRepository extends Repository<Transfer,Integer> implements ITransferRepository {
    @Override
    public Class<Transfer> getEntityClass() {
        return Transfer.class;
    }

}
