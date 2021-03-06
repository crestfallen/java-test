/**
 * @(#)ContextBinding.java 6.3.9 09/10/02
 * <p>
 * Copyright 2000-2010 MyMMSC Software Foundation (MSF), Inc. All rights reserved.
 * MyMMSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package test.mymmsc.api.assembly;

import org.mymmsc.api.context.ApiContext;

import javax.naming.NamingException;

/**
 * @author WangFeng(wangfeng@yeah.net)
 * @version 6.3.9 09/10/02
 * @since mymmsc-test 6.3.9
 */
public class ContextBinding {

    /**
     * ContextBinding
     */
    public ContextBinding() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) throws NamingException {

        String name = "jdbc/boss";
        String a = "10";
        ApiContext.bind(name, a);
        String b = ApiContext.lookup(name);
        System.out.println("b = " + b);
    }
}
