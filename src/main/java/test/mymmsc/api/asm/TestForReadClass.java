package test.mymmsc.api.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TestForReadClass {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            ClassReader reader = new ClassReader(
                    "test.mymmsc.api.asm.ForReadClass");
            ClassNode cn = new ClassNode();
            reader.accept(cn, 0);
            List<MethodNode> methodList = cn.methods;
            for (MethodNode md : methodList) {
                System.out.println(md.name);
                System.out.println(md.access);
                System.out.println(md.desc);
                System.out.println(md.signature);
                List<LocalVariableNode> lvNodeList = md.localVariables;
                for (LocalVariableNode lvn : lvNodeList) {
                    System.out.println("Local name: " + lvn.name);
                    System.out.println("Local name: " + lvn.start.getLabel());
                    System.out.println("Local name: " + lvn.desc);
                    System.out.println("Local name: " + lvn.signature);
                }
                Iterator<AbstractInsnNode> instraIter = md.instructions
                        .iterator();
                while (instraIter.hasNext()) {
                    AbstractInsnNode abi = instraIter.next();
                    if (abi instanceof LdcInsnNode) {
                        LdcInsnNode ldcI = (LdcInsnNode) abi;
                        System.out.println("LDC node value: " + ldcI.cst);
                    }
                }
            }
            MethodVisitor mv = cn.visitMethod(Opcodes.AALOAD, "<init>", Type
                    .getType(String.class).toString(), null, null);
            mv.visitFieldInsn(Opcodes.GETFIELD,
                    Type.getInternalName(String.class), "str",
                    Type.getType(String.class).toString());
            System.out.println("----------< " + cn.name + " >----------");
            List<FieldNode> fieldList = cn.fields;
            for (FieldNode fieldNode : fieldList) {
                System.out.println("Field name: " + fieldNode.name);
                System.out.println("Field desc: " + fieldNode.desc);
                System.out.println("Filed value: " + fieldNode.value);
                System.out.println("Filed access: " + fieldNode.access);
                if (fieldNode.visibleAnnotations != null) {
                    for (AnnotationNode anNode : fieldNode.visibleAnnotations) {
                        System.out.println(anNode.desc);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
