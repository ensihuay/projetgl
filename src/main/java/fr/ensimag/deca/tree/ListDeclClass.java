package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.log4j.Logger;

/**
 *
 * @author gl41
 * @date 01/01/2016
 */
public class ListDeclClass extends TreeList<AbstractDeclClass> {
    private static final Logger LOG = Logger.getLogger(ListDeclClass.class);
    
    @Override
    public void decompile(IndentPrintStream s) {
        for (AbstractDeclClass c : getList()) {
            c.decompile(s);
            s.println();
        }
    }

    /**
     * Attribute grammar's pass 1
     */
    void verifyListClass(DecacCompiler compiler) throws ContextualError {
        LOG.debug("verify listClass: start");
        for (AbstractDeclClass classDecl : getList()) {
            classDecl.verifyClass(compiler);
        }
    }

    /**
     * Attribute grammar's pass 2
     */
    public void verifyListClassMembers(DecacCompiler compiler) throws ContextualError {
        for (AbstractDeclClass classDecl : getList()) {
            classDecl.verifyClassMembers(compiler);
        }
    }
    
    /**
     * Attribute grammar's pass 3
     */
    public void verifyListClassBody(DecacCompiler compiler) throws ContextualError {
        for (AbstractDeclClass classDecl : getList()) {
            classDecl.verifyClassBody(compiler);
        }
        LOG.debug("verify listClass: end");
    }


}
