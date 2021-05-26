package com.merchant.demo.log;

import lombok.Data;

@Data
public class DbLog {
    private String dbLogSqno;      //sequence
    private String apiOffererName; //  apioffererName?  
    private String callApiName;    // apiname -> url call name? 
    private InOutDivision inOutDivision;  
    private String transactionId; //srci x ->  transaction of log 
    private String dbLogData;
    private String dateOfDbLogCreated;
    private String responseCode;   // not used 
}

/**


               INSERT
               INTO    DGT.TDSSRCSTRNSLG
                       (
                               LG_SQNO
                             , REG_DTM
                             , API_SUPR_NM
                             , CALL_API_NM
                             , IOT_DV_CD
                             , SRCI_TRNS_ID
                             , TS_CTNT
                             , SRCS_TRNS_RSPN_CD
                             , SYS_SVC_ID
                             , SYS_CTNR_ID
                             , SYS_CHNGR_ID
                             , SYS_CRET_DTM
                             , SYS_CHNG_DTM
                       )
                       VALUES
                       (
                               #{dbLog.dbLogSqno}
                             , #{dbLog.dateOfDbLogCreated}
                             , #{dbLog.apiOffererName}
                             , #{dbLog.callApiName}
                             , #{dbLog.inOutDivision, typeHandler = com.bccard.bcsrc.mapper.typehandler.SrcEnumTypeHandler}
                             , #{dbLog.srciTransactionId}
                             , #{dbLog.dbLogData}
                             , #{dbLog.responseCode}
                             , #{systemInfo.sysSvcId}
                             , #{systemInfo.sysCtnrId}
                             , #{systemInfo.sysChngrId}
                             , SYSTIMESTAMP
                             , SYSTIMESTAMP

                       )

 
*/