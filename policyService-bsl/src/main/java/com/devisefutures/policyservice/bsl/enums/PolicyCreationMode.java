package com.devisefutures.policyservice.bsl.enums;

/**
 * Implemented validation policy creation modes
 */
public enum PolicyCreationMode {
    SAFE, // The policy is assembled based on DSS default policy
    FREE // The policy is not based in any standard (what you ask is what you get)
}
