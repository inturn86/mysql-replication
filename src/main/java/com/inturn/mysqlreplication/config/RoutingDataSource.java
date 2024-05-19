package com.inturn.mysqlreplication.config;

import com.inturn.mysqlreplication.config.vo.DatabaseConsts;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

		System.out.println("Transaction의 Read Only가 " + isReadOnly + " 입니다.");

		if (isReadOnly) {
			System.out.println("Slave 서버로 요청합니다.");
			return DatabaseConsts.SLAVE_SERVER;
		}
		else {
			System.out.println("Master 서버로 요청합니다.");
			return DatabaseConsts.MASTER_SERVER;
		}
	}
}
