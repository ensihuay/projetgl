package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.*;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * @author gl41
 * @date 01/01/2016
 */
public class DeclVarSet extends AbstractDeclVarSet {
    public AbstractIdentifier getType() {
        return type;
    }

    public ListDeclVar getDeclVars() {
        return declVars;
    }

    private AbstractIdentifier type;
    private ListDeclVar declVars;

    @Override
    public int returnSP() {
        return declVars.size();
    }

    public DeclVarSet(AbstractIdentifier type, ListDeclVar declVars) {
        super();
        Validate.notNull(type);
        Validate.notNull(declVars);
        Validate.isTrue(!declVars.isEmpty(),
                "A list of variable declarations cannot be empty");
        this.type = type;
        this.declVars = declVars;
    }

    @Override
    protected Type verifyDeclVarSet(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {

        Type t = type.verifyType(compiler);

        //erreur si type = void
        if(t.isVoid()) {
            throw new ContextualError("A variable can not be declared as void.", getLocation());
        }

        for(AbstractDeclVar var : declVars.getList()) {
            var.verifyDeclVar(t, compiler, localEnv, currentClass);
        }

        return type.getType();
    }

    @Override
    protected void codegenDeclVarSet(DecacCompiler compiler) {
        // run codegen on each declaration
        for(AbstractDeclVar declVar : getDeclVars().getList()){
            declVar.codeGenDecl(compiler);
        }
    }

    protected void codePreGenDeclVarSet(DecacCompiler compiler){
        for(AbstractDeclVar declVar : getDeclVars().getList()){
            declVar.codePreGenDecl(compiler);
        }

    }
    @Override
    protected void codegenDeclVarSetMethod(DecacCompiler compiler) {
        // run codegen on each declaration
        for(AbstractDeclVar declVar : getDeclVars().getList()){
            declVar.codeGenDeclMethod(compiler);
            if(type.getDefinition().isClass()){

            }
        }
    }



    @Override
    public void decompile(IndentPrintStream s) {
        s.print(getType()+" ");
        int i=getDeclVars().size();
        for( AbstractDeclVar a : getDeclVars().getList()){
            a.decompile(s);
            i--;
            if(i==0){
                s.println(";");
            }
            else{
                s.print(",");
            }
        }
    }

    @Override
    protected
    void iterChildren(TreeFunction f) {
        type.iter(f);
        declVars.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        declVars.prettyPrint(s, prefix, true);
    }

}
