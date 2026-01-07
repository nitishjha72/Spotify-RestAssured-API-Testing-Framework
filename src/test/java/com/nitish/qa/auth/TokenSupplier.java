package com.nitish.qa.auth;

import com.nitish.qa.pojo.Token;

@FunctionalInterface
public interface TokenSupplier {

    Token fetch();
}
