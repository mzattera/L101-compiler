package org.mzattera.dsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.mzattera.dsl.services.L101GrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalL101Parser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_NUMBER", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'SHORT'", "'LONG'", "','", "';'", "'IN'", "'B'", "'B/'", "'C'", "'C/'", "'D'", "'D/'", "'E'", "'E/'", "'F'", "'F/'", "'='", "'+='", "'-='", "'/='", "'*='", "'%='", "'<->'", "'+'", "'-'", "'*'", "'/'", "'%'", "'SQ'", "'SQRT'", "'INT'", "'DEC'", "'INV'", "'NEG'", "'ABS'", "'('", "')'", "'A'", "'INPUT'", "'ON'", "'V'", "'W'", "'Y'", "'Z'", "':'", "'->'", "'PRINT'", "'NL'", "'GOTO'", "'CONTINUE'", "'BREAK'", "'IF'", "'THEN'", "'ELSE'", "'END'", "'UNTIL'", "'DO'", "'FOR'", "'WHILE'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__59=59;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__55=55;
    public static final int T__12=12;
    public static final int T__56=56;
    public static final int T__13=13;
    public static final int T__57=57;
    public static final int T__14=14;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__10=10;
    public static final int T__54=54;
    public static final int T__9=9;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=6;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__20=20;
    public static final int T__64=64;
    public static final int T__21=21;
    public static final int T__65=65;
    public static final int RULE_SL_COMMENT=7;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=8;
    public static final int RULE_NUMBER=5;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalL101Parser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalL101Parser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalL101Parser.tokenNames; }
    public String getGrammarFileName() { return "InternalL101.g"; }



     	private L101GrammarAccess grammarAccess;

        public InternalL101Parser(TokenStream input, L101GrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Program";
       	}

       	@Override
       	protected L101GrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleProgram"
    // InternalL101.g:64:1: entryRuleProgram returns [EObject current=null] : iv_ruleProgram= ruleProgram EOF ;
    public final EObject entryRuleProgram() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProgram = null;


        try {
            // InternalL101.g:64:48: (iv_ruleProgram= ruleProgram EOF )
            // InternalL101.g:65:2: iv_ruleProgram= ruleProgram EOF
            {
             newCompositeNode(grammarAccess.getProgramRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProgram=ruleProgram();

            state._fsp--;

             current =iv_ruleProgram; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProgram"


    // $ANTLR start "ruleProgram"
    // InternalL101.g:71:1: ruleProgram returns [EObject current=null] : ( ( (lv_varDeclarations_0_0= ruleVariableDeclaration ) )* ( (lv_body_1_0= ruleStatements ) ) ) ;
    public final EObject ruleProgram() throws RecognitionException {
        EObject current = null;

        EObject lv_varDeclarations_0_0 = null;

        EObject lv_body_1_0 = null;



        	enterRule();

        try {
            // InternalL101.g:77:2: ( ( ( (lv_varDeclarations_0_0= ruleVariableDeclaration ) )* ( (lv_body_1_0= ruleStatements ) ) ) )
            // InternalL101.g:78:2: ( ( (lv_varDeclarations_0_0= ruleVariableDeclaration ) )* ( (lv_body_1_0= ruleStatements ) ) )
            {
            // InternalL101.g:78:2: ( ( (lv_varDeclarations_0_0= ruleVariableDeclaration ) )* ( (lv_body_1_0= ruleStatements ) ) )
            // InternalL101.g:79:3: ( (lv_varDeclarations_0_0= ruleVariableDeclaration ) )* ( (lv_body_1_0= ruleStatements ) )
            {
            // InternalL101.g:79:3: ( (lv_varDeclarations_0_0= ruleVariableDeclaration ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=9 && LA1_0<=10)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalL101.g:80:4: (lv_varDeclarations_0_0= ruleVariableDeclaration )
            	    {
            	    // InternalL101.g:80:4: (lv_varDeclarations_0_0= ruleVariableDeclaration )
            	    // InternalL101.g:81:5: lv_varDeclarations_0_0= ruleVariableDeclaration
            	    {

            	    					newCompositeNode(grammarAccess.getProgramAccess().getVarDeclarationsVariableDeclarationParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_varDeclarations_0_0=ruleVariableDeclaration();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getProgramRule());
            	    					}
            	    					add(
            	    						current,
            	    						"varDeclarations",
            	    						lv_varDeclarations_0_0,
            	    						"org.mzattera.dsl.L101.VariableDeclaration");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalL101.g:98:3: ( (lv_body_1_0= ruleStatements ) )
            // InternalL101.g:99:4: (lv_body_1_0= ruleStatements )
            {
            // InternalL101.g:99:4: (lv_body_1_0= ruleStatements )
            // InternalL101.g:100:5: lv_body_1_0= ruleStatements
            {

            					newCompositeNode(grammarAccess.getProgramAccess().getBodyStatementsParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_body_1_0=ruleStatements();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getProgramRule());
            					}
            					set(
            						current,
            						"body",
            						lv_body_1_0,
            						"org.mzattera.dsl.L101.Statements");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProgram"


    // $ANTLR start "entryRuleVariableDeclaration"
    // InternalL101.g:121:1: entryRuleVariableDeclaration returns [EObject current=null] : iv_ruleVariableDeclaration= ruleVariableDeclaration EOF ;
    public final EObject entryRuleVariableDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableDeclaration = null;


        try {
            // InternalL101.g:121:60: (iv_ruleVariableDeclaration= ruleVariableDeclaration EOF )
            // InternalL101.g:122:2: iv_ruleVariableDeclaration= ruleVariableDeclaration EOF
            {
             newCompositeNode(grammarAccess.getVariableDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariableDeclaration=ruleVariableDeclaration();

            state._fsp--;

             current =iv_ruleVariableDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariableDeclaration"


    // $ANTLR start "ruleVariableDeclaration"
    // InternalL101.g:128:1: ruleVariableDeclaration returns [EObject current=null] : ( ( ( (lv_short_0_0= 'SHORT' ) ) | otherlv_1= 'LONG' ) ( (lv_vars_2_0= ruleVariable ) ) (otherlv_3= ',' ( (lv_vars_4_0= ruleVariable ) ) )* otherlv_5= ';' ) ;
    public final EObject ruleVariableDeclaration() throws RecognitionException {
        EObject current = null;

        Token lv_short_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_vars_2_0 = null;

        EObject lv_vars_4_0 = null;



        	enterRule();

        try {
            // InternalL101.g:134:2: ( ( ( ( (lv_short_0_0= 'SHORT' ) ) | otherlv_1= 'LONG' ) ( (lv_vars_2_0= ruleVariable ) ) (otherlv_3= ',' ( (lv_vars_4_0= ruleVariable ) ) )* otherlv_5= ';' ) )
            // InternalL101.g:135:2: ( ( ( (lv_short_0_0= 'SHORT' ) ) | otherlv_1= 'LONG' ) ( (lv_vars_2_0= ruleVariable ) ) (otherlv_3= ',' ( (lv_vars_4_0= ruleVariable ) ) )* otherlv_5= ';' )
            {
            // InternalL101.g:135:2: ( ( ( (lv_short_0_0= 'SHORT' ) ) | otherlv_1= 'LONG' ) ( (lv_vars_2_0= ruleVariable ) ) (otherlv_3= ',' ( (lv_vars_4_0= ruleVariable ) ) )* otherlv_5= ';' )
            // InternalL101.g:136:3: ( ( (lv_short_0_0= 'SHORT' ) ) | otherlv_1= 'LONG' ) ( (lv_vars_2_0= ruleVariable ) ) (otherlv_3= ',' ( (lv_vars_4_0= ruleVariable ) ) )* otherlv_5= ';'
            {
            // InternalL101.g:136:3: ( ( (lv_short_0_0= 'SHORT' ) ) | otherlv_1= 'LONG' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==9) ) {
                alt2=1;
            }
            else if ( (LA2_0==10) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalL101.g:137:4: ( (lv_short_0_0= 'SHORT' ) )
                    {
                    // InternalL101.g:137:4: ( (lv_short_0_0= 'SHORT' ) )
                    // InternalL101.g:138:5: (lv_short_0_0= 'SHORT' )
                    {
                    // InternalL101.g:138:5: (lv_short_0_0= 'SHORT' )
                    // InternalL101.g:139:6: lv_short_0_0= 'SHORT'
                    {
                    lv_short_0_0=(Token)match(input,9,FOLLOW_4); 

                    						newLeafNode(lv_short_0_0, grammarAccess.getVariableDeclarationAccess().getShortSHORTKeyword_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableDeclarationRule());
                    						}
                    						setWithLastConsumed(current, "short", lv_short_0_0 != null, "SHORT");
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalL101.g:152:4: otherlv_1= 'LONG'
                    {
                    otherlv_1=(Token)match(input,10,FOLLOW_4); 

                    				newLeafNode(otherlv_1, grammarAccess.getVariableDeclarationAccess().getLONGKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalL101.g:157:3: ( (lv_vars_2_0= ruleVariable ) )
            // InternalL101.g:158:4: (lv_vars_2_0= ruleVariable )
            {
            // InternalL101.g:158:4: (lv_vars_2_0= ruleVariable )
            // InternalL101.g:159:5: lv_vars_2_0= ruleVariable
            {

            					newCompositeNode(grammarAccess.getVariableDeclarationAccess().getVarsVariableParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_5);
            lv_vars_2_0=ruleVariable();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVariableDeclarationRule());
            					}
            					add(
            						current,
            						"vars",
            						lv_vars_2_0,
            						"org.mzattera.dsl.L101.Variable");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalL101.g:176:3: (otherlv_3= ',' ( (lv_vars_4_0= ruleVariable ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==11) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalL101.g:177:4: otherlv_3= ',' ( (lv_vars_4_0= ruleVariable ) )
            	    {
            	    otherlv_3=(Token)match(input,11,FOLLOW_4); 

            	    				newLeafNode(otherlv_3, grammarAccess.getVariableDeclarationAccess().getCommaKeyword_2_0());
            	    			
            	    // InternalL101.g:181:4: ( (lv_vars_4_0= ruleVariable ) )
            	    // InternalL101.g:182:5: (lv_vars_4_0= ruleVariable )
            	    {
            	    // InternalL101.g:182:5: (lv_vars_4_0= ruleVariable )
            	    // InternalL101.g:183:6: lv_vars_4_0= ruleVariable
            	    {

            	    						newCompositeNode(grammarAccess.getVariableDeclarationAccess().getVarsVariableParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_5);
            	    lv_vars_4_0=ruleVariable();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getVariableDeclarationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"vars",
            	    							lv_vars_4_0,
            	    							"org.mzattera.dsl.L101.Variable");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_5=(Token)match(input,12,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getVariableDeclarationAccess().getSemicolonKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariableDeclaration"


    // $ANTLR start "entryRuleVariable"
    // InternalL101.g:209:1: entryRuleVariable returns [EObject current=null] : iv_ruleVariable= ruleVariable EOF ;
    public final EObject entryRuleVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariable = null;


        try {
            // InternalL101.g:209:49: (iv_ruleVariable= ruleVariable EOF )
            // InternalL101.g:210:2: iv_ruleVariable= ruleVariable EOF
            {
             newCompositeNode(grammarAccess.getVariableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariable=ruleVariable();

            state._fsp--;

             current =iv_ruleVariable; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariable"


    // $ANTLR start "ruleVariable"
    // InternalL101.g:216:1: ruleVariable returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'IN' ( ( (lv_reg_2_1= 'B' | lv_reg_2_2= 'B/' | lv_reg_2_3= 'C' | lv_reg_2_4= 'C/' | lv_reg_2_5= 'D' | lv_reg_2_6= 'D/' | lv_reg_2_7= 'E' | lv_reg_2_8= 'E/' | lv_reg_2_9= 'F' | lv_reg_2_10= 'F/' ) ) ) ) ;
    public final EObject ruleVariable() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_reg_2_1=null;
        Token lv_reg_2_2=null;
        Token lv_reg_2_3=null;
        Token lv_reg_2_4=null;
        Token lv_reg_2_5=null;
        Token lv_reg_2_6=null;
        Token lv_reg_2_7=null;
        Token lv_reg_2_8=null;
        Token lv_reg_2_9=null;
        Token lv_reg_2_10=null;


        	enterRule();

        try {
            // InternalL101.g:222:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'IN' ( ( (lv_reg_2_1= 'B' | lv_reg_2_2= 'B/' | lv_reg_2_3= 'C' | lv_reg_2_4= 'C/' | lv_reg_2_5= 'D' | lv_reg_2_6= 'D/' | lv_reg_2_7= 'E' | lv_reg_2_8= 'E/' | lv_reg_2_9= 'F' | lv_reg_2_10= 'F/' ) ) ) ) )
            // InternalL101.g:223:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'IN' ( ( (lv_reg_2_1= 'B' | lv_reg_2_2= 'B/' | lv_reg_2_3= 'C' | lv_reg_2_4= 'C/' | lv_reg_2_5= 'D' | lv_reg_2_6= 'D/' | lv_reg_2_7= 'E' | lv_reg_2_8= 'E/' | lv_reg_2_9= 'F' | lv_reg_2_10= 'F/' ) ) ) )
            {
            // InternalL101.g:223:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'IN' ( ( (lv_reg_2_1= 'B' | lv_reg_2_2= 'B/' | lv_reg_2_3= 'C' | lv_reg_2_4= 'C/' | lv_reg_2_5= 'D' | lv_reg_2_6= 'D/' | lv_reg_2_7= 'E' | lv_reg_2_8= 'E/' | lv_reg_2_9= 'F' | lv_reg_2_10= 'F/' ) ) ) )
            // InternalL101.g:224:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'IN' ( ( (lv_reg_2_1= 'B' | lv_reg_2_2= 'B/' | lv_reg_2_3= 'C' | lv_reg_2_4= 'C/' | lv_reg_2_5= 'D' | lv_reg_2_6= 'D/' | lv_reg_2_7= 'E' | lv_reg_2_8= 'E/' | lv_reg_2_9= 'F' | lv_reg_2_10= 'F/' ) ) )
            {
            // InternalL101.g:224:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalL101.g:225:4: (lv_name_0_0= RULE_ID )
            {
            // InternalL101.g:225:4: (lv_name_0_0= RULE_ID )
            // InternalL101.g:226:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_name_0_0, grammarAccess.getVariableAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVariableRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.mzattera.dsl.L101.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,13,FOLLOW_7); 

            			newLeafNode(otherlv_1, grammarAccess.getVariableAccess().getINKeyword_1());
            		
            // InternalL101.g:246:3: ( ( (lv_reg_2_1= 'B' | lv_reg_2_2= 'B/' | lv_reg_2_3= 'C' | lv_reg_2_4= 'C/' | lv_reg_2_5= 'D' | lv_reg_2_6= 'D/' | lv_reg_2_7= 'E' | lv_reg_2_8= 'E/' | lv_reg_2_9= 'F' | lv_reg_2_10= 'F/' ) ) )
            // InternalL101.g:247:4: ( (lv_reg_2_1= 'B' | lv_reg_2_2= 'B/' | lv_reg_2_3= 'C' | lv_reg_2_4= 'C/' | lv_reg_2_5= 'D' | lv_reg_2_6= 'D/' | lv_reg_2_7= 'E' | lv_reg_2_8= 'E/' | lv_reg_2_9= 'F' | lv_reg_2_10= 'F/' ) )
            {
            // InternalL101.g:247:4: ( (lv_reg_2_1= 'B' | lv_reg_2_2= 'B/' | lv_reg_2_3= 'C' | lv_reg_2_4= 'C/' | lv_reg_2_5= 'D' | lv_reg_2_6= 'D/' | lv_reg_2_7= 'E' | lv_reg_2_8= 'E/' | lv_reg_2_9= 'F' | lv_reg_2_10= 'F/' ) )
            // InternalL101.g:248:5: (lv_reg_2_1= 'B' | lv_reg_2_2= 'B/' | lv_reg_2_3= 'C' | lv_reg_2_4= 'C/' | lv_reg_2_5= 'D' | lv_reg_2_6= 'D/' | lv_reg_2_7= 'E' | lv_reg_2_8= 'E/' | lv_reg_2_9= 'F' | lv_reg_2_10= 'F/' )
            {
            // InternalL101.g:248:5: (lv_reg_2_1= 'B' | lv_reg_2_2= 'B/' | lv_reg_2_3= 'C' | lv_reg_2_4= 'C/' | lv_reg_2_5= 'D' | lv_reg_2_6= 'D/' | lv_reg_2_7= 'E' | lv_reg_2_8= 'E/' | lv_reg_2_9= 'F' | lv_reg_2_10= 'F/' )
            int alt4=10;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt4=1;
                }
                break;
            case 15:
                {
                alt4=2;
                }
                break;
            case 16:
                {
                alt4=3;
                }
                break;
            case 17:
                {
                alt4=4;
                }
                break;
            case 18:
                {
                alt4=5;
                }
                break;
            case 19:
                {
                alt4=6;
                }
                break;
            case 20:
                {
                alt4=7;
                }
                break;
            case 21:
                {
                alt4=8;
                }
                break;
            case 22:
                {
                alt4=9;
                }
                break;
            case 23:
                {
                alt4=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalL101.g:249:6: lv_reg_2_1= 'B'
                    {
                    lv_reg_2_1=(Token)match(input,14,FOLLOW_2); 

                    						newLeafNode(lv_reg_2_1, grammarAccess.getVariableAccess().getRegBKeyword_2_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableRule());
                    						}
                    						setWithLastConsumed(current, "reg", lv_reg_2_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalL101.g:260:6: lv_reg_2_2= 'B/'
                    {
                    lv_reg_2_2=(Token)match(input,15,FOLLOW_2); 

                    						newLeafNode(lv_reg_2_2, grammarAccess.getVariableAccess().getRegBKeyword_2_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableRule());
                    						}
                    						setWithLastConsumed(current, "reg", lv_reg_2_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalL101.g:271:6: lv_reg_2_3= 'C'
                    {
                    lv_reg_2_3=(Token)match(input,16,FOLLOW_2); 

                    						newLeafNode(lv_reg_2_3, grammarAccess.getVariableAccess().getRegCKeyword_2_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableRule());
                    						}
                    						setWithLastConsumed(current, "reg", lv_reg_2_3, null);
                    					

                    }
                    break;
                case 4 :
                    // InternalL101.g:282:6: lv_reg_2_4= 'C/'
                    {
                    lv_reg_2_4=(Token)match(input,17,FOLLOW_2); 

                    						newLeafNode(lv_reg_2_4, grammarAccess.getVariableAccess().getRegCKeyword_2_0_3());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableRule());
                    						}
                    						setWithLastConsumed(current, "reg", lv_reg_2_4, null);
                    					

                    }
                    break;
                case 5 :
                    // InternalL101.g:293:6: lv_reg_2_5= 'D'
                    {
                    lv_reg_2_5=(Token)match(input,18,FOLLOW_2); 

                    						newLeafNode(lv_reg_2_5, grammarAccess.getVariableAccess().getRegDKeyword_2_0_4());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableRule());
                    						}
                    						setWithLastConsumed(current, "reg", lv_reg_2_5, null);
                    					

                    }
                    break;
                case 6 :
                    // InternalL101.g:304:6: lv_reg_2_6= 'D/'
                    {
                    lv_reg_2_6=(Token)match(input,19,FOLLOW_2); 

                    						newLeafNode(lv_reg_2_6, grammarAccess.getVariableAccess().getRegDKeyword_2_0_5());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableRule());
                    						}
                    						setWithLastConsumed(current, "reg", lv_reg_2_6, null);
                    					

                    }
                    break;
                case 7 :
                    // InternalL101.g:315:6: lv_reg_2_7= 'E'
                    {
                    lv_reg_2_7=(Token)match(input,20,FOLLOW_2); 

                    						newLeafNode(lv_reg_2_7, grammarAccess.getVariableAccess().getRegEKeyword_2_0_6());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableRule());
                    						}
                    						setWithLastConsumed(current, "reg", lv_reg_2_7, null);
                    					

                    }
                    break;
                case 8 :
                    // InternalL101.g:326:6: lv_reg_2_8= 'E/'
                    {
                    lv_reg_2_8=(Token)match(input,21,FOLLOW_2); 

                    						newLeafNode(lv_reg_2_8, grammarAccess.getVariableAccess().getRegEKeyword_2_0_7());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableRule());
                    						}
                    						setWithLastConsumed(current, "reg", lv_reg_2_8, null);
                    					

                    }
                    break;
                case 9 :
                    // InternalL101.g:337:6: lv_reg_2_9= 'F'
                    {
                    lv_reg_2_9=(Token)match(input,22,FOLLOW_2); 

                    						newLeafNode(lv_reg_2_9, grammarAccess.getVariableAccess().getRegFKeyword_2_0_8());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableRule());
                    						}
                    						setWithLastConsumed(current, "reg", lv_reg_2_9, null);
                    					

                    }
                    break;
                case 10 :
                    // InternalL101.g:348:6: lv_reg_2_10= 'F/'
                    {
                    lv_reg_2_10=(Token)match(input,23,FOLLOW_2); 

                    						newLeafNode(lv_reg_2_10, grammarAccess.getVariableAccess().getRegFKeyword_2_0_9());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableRule());
                    						}
                    						setWithLastConsumed(current, "reg", lv_reg_2_10, null);
                    					

                    }
                    break;

            }


            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariable"


    // $ANTLR start "entryRuleVariableAccess"
    // InternalL101.g:365:1: entryRuleVariableAccess returns [EObject current=null] : iv_ruleVariableAccess= ruleVariableAccess EOF ;
    public final EObject entryRuleVariableAccess() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableAccess = null;


        try {
            // InternalL101.g:365:55: (iv_ruleVariableAccess= ruleVariableAccess EOF )
            // InternalL101.g:366:2: iv_ruleVariableAccess= ruleVariableAccess EOF
            {
             newCompositeNode(grammarAccess.getVariableAccessRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariableAccess=ruleVariableAccess();

            state._fsp--;

             current =iv_ruleVariableAccess; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariableAccess"


    // $ANTLR start "ruleVariableAccess"
    // InternalL101.g:372:1: ruleVariableAccess returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleVariableAccess() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalL101.g:378:2: ( ( () ( (otherlv_1= RULE_ID ) ) ) )
            // InternalL101.g:379:2: ( () ( (otherlv_1= RULE_ID ) ) )
            {
            // InternalL101.g:379:2: ( () ( (otherlv_1= RULE_ID ) ) )
            // InternalL101.g:380:3: () ( (otherlv_1= RULE_ID ) )
            {
            // InternalL101.g:380:3: ()
            // InternalL101.g:381:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVariableAccessAccess().getVariableAccessAction_0(),
            					current);
            			

            }

            // InternalL101.g:387:3: ( (otherlv_1= RULE_ID ) )
            // InternalL101.g:388:4: (otherlv_1= RULE_ID )
            {
            // InternalL101.g:388:4: (otherlv_1= RULE_ID )
            // InternalL101.g:389:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVariableAccessRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_1, grammarAccess.getVariableAccessAccess().getStoreVariableCrossReference_1_0());
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariableAccess"


    // $ANTLR start "entryRuleVariableOrUserInput"
    // InternalL101.g:404:1: entryRuleVariableOrUserInput returns [EObject current=null] : iv_ruleVariableOrUserInput= ruleVariableOrUserInput EOF ;
    public final EObject entryRuleVariableOrUserInput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableOrUserInput = null;


        try {
            // InternalL101.g:404:60: (iv_ruleVariableOrUserInput= ruleVariableOrUserInput EOF )
            // InternalL101.g:405:2: iv_ruleVariableOrUserInput= ruleVariableOrUserInput EOF
            {
             newCompositeNode(grammarAccess.getVariableOrUserInputRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariableOrUserInput=ruleVariableOrUserInput();

            state._fsp--;

             current =iv_ruleVariableOrUserInput; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariableOrUserInput"


    // $ANTLR start "ruleVariableOrUserInput"
    // InternalL101.g:411:1: ruleVariableOrUserInput returns [EObject current=null] : (this_VariableAccess_0= ruleVariableAccess | this_UserInput_1= ruleUserInput ) ;
    public final EObject ruleVariableOrUserInput() throws RecognitionException {
        EObject current = null;

        EObject this_VariableAccess_0 = null;

        EObject this_UserInput_1 = null;



        	enterRule();

        try {
            // InternalL101.g:417:2: ( (this_VariableAccess_0= ruleVariableAccess | this_UserInput_1= ruleUserInput ) )
            // InternalL101.g:418:2: (this_VariableAccess_0= ruleVariableAccess | this_UserInput_1= ruleUserInput )
            {
            // InternalL101.g:418:2: (this_VariableAccess_0= ruleVariableAccess | this_UserInput_1= ruleUserInput )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                alt5=1;
            }
            else if ( (LA5_0==46) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalL101.g:419:3: this_VariableAccess_0= ruleVariableAccess
                    {

                    			newCompositeNode(grammarAccess.getVariableOrUserInputAccess().getVariableAccessParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_VariableAccess_0=ruleVariableAccess();

                    state._fsp--;


                    			current = this_VariableAccess_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalL101.g:428:3: this_UserInput_1= ruleUserInput
                    {

                    			newCompositeNode(grammarAccess.getVariableOrUserInputAccess().getUserInputParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_UserInput_1=ruleUserInput();

                    state._fsp--;


                    			current = this_UserInput_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariableOrUserInput"


    // $ANTLR start "entryRuleAssignmentStatement"
    // InternalL101.g:440:1: entryRuleAssignmentStatement returns [EObject current=null] : iv_ruleAssignmentStatement= ruleAssignmentStatement EOF ;
    public final EObject entryRuleAssignmentStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignmentStatement = null;


        try {
            // InternalL101.g:440:60: (iv_ruleAssignmentStatement= ruleAssignmentStatement EOF )
            // InternalL101.g:441:2: iv_ruleAssignmentStatement= ruleAssignmentStatement EOF
            {
             newCompositeNode(grammarAccess.getAssignmentStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssignmentStatement=ruleAssignmentStatement();

            state._fsp--;

             current =iv_ruleAssignmentStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssignmentStatement"


    // $ANTLR start "ruleAssignmentStatement"
    // InternalL101.g:447:1: ruleAssignmentStatement returns [EObject current=null] : ( ( ( (lv_op_0_0= '=' ) ) ( (lv_operand_1_0= ruleExpression ) ) ) | ( ( ( (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' ) ) ) ( ( (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant ) ) ) ) | ( ( (lv_store_4_0= ruleVariableAccess ) ) ( (lv_op_5_0= '<->' ) ) ( (lv_operand_6_0= ruleExpression ) ) ) ) ;
    public final EObject ruleAssignmentStatement() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_0=null;
        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        Token lv_op_2_4=null;
        Token lv_op_2_5=null;
        Token lv_op_5_0=null;
        EObject lv_operand_1_0 = null;

        EObject lv_operand_3_1 = null;

        EObject lv_operand_3_2 = null;

        EObject lv_operand_3_3 = null;

        EObject lv_store_4_0 = null;

        EObject lv_operand_6_0 = null;



        	enterRule();

        try {
            // InternalL101.g:453:2: ( ( ( ( (lv_op_0_0= '=' ) ) ( (lv_operand_1_0= ruleExpression ) ) ) | ( ( ( (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' ) ) ) ( ( (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant ) ) ) ) | ( ( (lv_store_4_0= ruleVariableAccess ) ) ( (lv_op_5_0= '<->' ) ) ( (lv_operand_6_0= ruleExpression ) ) ) ) )
            // InternalL101.g:454:2: ( ( ( (lv_op_0_0= '=' ) ) ( (lv_operand_1_0= ruleExpression ) ) ) | ( ( ( (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' ) ) ) ( ( (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant ) ) ) ) | ( ( (lv_store_4_0= ruleVariableAccess ) ) ( (lv_op_5_0= '<->' ) ) ( (lv_operand_6_0= ruleExpression ) ) ) )
            {
            // InternalL101.g:454:2: ( ( ( (lv_op_0_0= '=' ) ) ( (lv_operand_1_0= ruleExpression ) ) ) | ( ( ( (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' ) ) ) ( ( (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant ) ) ) ) | ( ( (lv_store_4_0= ruleVariableAccess ) ) ( (lv_op_5_0= '<->' ) ) ( (lv_operand_6_0= ruleExpression ) ) ) )
            int alt8=3;
            switch ( input.LA(1) ) {
            case 24:
                {
                alt8=1;
                }
                break;
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                {
                alt8=2;
                }
                break;
            case RULE_ID:
                {
                alt8=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalL101.g:455:3: ( ( (lv_op_0_0= '=' ) ) ( (lv_operand_1_0= ruleExpression ) ) )
                    {
                    // InternalL101.g:455:3: ( ( (lv_op_0_0= '=' ) ) ( (lv_operand_1_0= ruleExpression ) ) )
                    // InternalL101.g:456:4: ( (lv_op_0_0= '=' ) ) ( (lv_operand_1_0= ruleExpression ) )
                    {
                    // InternalL101.g:456:4: ( (lv_op_0_0= '=' ) )
                    // InternalL101.g:457:5: (lv_op_0_0= '=' )
                    {
                    // InternalL101.g:457:5: (lv_op_0_0= '=' )
                    // InternalL101.g:458:6: lv_op_0_0= '='
                    {
                    lv_op_0_0=(Token)match(input,24,FOLLOW_8); 

                    						newLeafNode(lv_op_0_0, grammarAccess.getAssignmentStatementAccess().getOpEqualsSignKeyword_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssignmentStatementRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_0, "=");
                    					

                    }


                    }

                    // InternalL101.g:470:4: ( (lv_operand_1_0= ruleExpression ) )
                    // InternalL101.g:471:5: (lv_operand_1_0= ruleExpression )
                    {
                    // InternalL101.g:471:5: (lv_operand_1_0= ruleExpression )
                    // InternalL101.g:472:6: lv_operand_1_0= ruleExpression
                    {

                    						newCompositeNode(grammarAccess.getAssignmentStatementAccess().getOperandExpressionParserRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_operand_1_0=ruleExpression();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAssignmentStatementRule());
                    						}
                    						set(
                    							current,
                    							"operand",
                    							lv_operand_1_0,
                    							"org.mzattera.dsl.L101.Expression");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalL101.g:491:3: ( ( ( (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' ) ) ) ( ( (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant ) ) ) )
                    {
                    // InternalL101.g:491:3: ( ( ( (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' ) ) ) ( ( (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant ) ) ) )
                    // InternalL101.g:492:4: ( ( (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' ) ) ) ( ( (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant ) ) )
                    {
                    // InternalL101.g:492:4: ( ( (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' ) ) )
                    // InternalL101.g:493:5: ( (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' ) )
                    {
                    // InternalL101.g:493:5: ( (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' ) )
                    // InternalL101.g:494:6: (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' )
                    {
                    // InternalL101.g:494:6: (lv_op_2_1= '+=' | lv_op_2_2= '-=' | lv_op_2_3= '/=' | lv_op_2_4= '*=' | lv_op_2_5= '%=' )
                    int alt6=5;
                    switch ( input.LA(1) ) {
                    case 25:
                        {
                        alt6=1;
                        }
                        break;
                    case 26:
                        {
                        alt6=2;
                        }
                        break;
                    case 27:
                        {
                        alt6=3;
                        }
                        break;
                    case 28:
                        {
                        alt6=4;
                        }
                        break;
                    case 29:
                        {
                        alt6=5;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 0, input);

                        throw nvae;
                    }

                    switch (alt6) {
                        case 1 :
                            // InternalL101.g:495:7: lv_op_2_1= '+='
                            {
                            lv_op_2_1=(Token)match(input,25,FOLLOW_9); 

                            							newLeafNode(lv_op_2_1, grammarAccess.getAssignmentStatementAccess().getOpPlusSignEqualsSignKeyword_1_0_0_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getAssignmentStatementRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_2_1, null);
                            						

                            }
                            break;
                        case 2 :
                            // InternalL101.g:506:7: lv_op_2_2= '-='
                            {
                            lv_op_2_2=(Token)match(input,26,FOLLOW_9); 

                            							newLeafNode(lv_op_2_2, grammarAccess.getAssignmentStatementAccess().getOpHyphenMinusEqualsSignKeyword_1_0_0_1());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getAssignmentStatementRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_2_2, null);
                            						

                            }
                            break;
                        case 3 :
                            // InternalL101.g:517:7: lv_op_2_3= '/='
                            {
                            lv_op_2_3=(Token)match(input,27,FOLLOW_9); 

                            							newLeafNode(lv_op_2_3, grammarAccess.getAssignmentStatementAccess().getOpSolidusEqualsSignKeyword_1_0_0_2());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getAssignmentStatementRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_2_3, null);
                            						

                            }
                            break;
                        case 4 :
                            // InternalL101.g:528:7: lv_op_2_4= '*='
                            {
                            lv_op_2_4=(Token)match(input,28,FOLLOW_9); 

                            							newLeafNode(lv_op_2_4, grammarAccess.getAssignmentStatementAccess().getOpAsteriskEqualsSignKeyword_1_0_0_3());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getAssignmentStatementRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_2_4, null);
                            						

                            }
                            break;
                        case 5 :
                            // InternalL101.g:539:7: lv_op_2_5= '%='
                            {
                            lv_op_2_5=(Token)match(input,29,FOLLOW_9); 

                            							newLeafNode(lv_op_2_5, grammarAccess.getAssignmentStatementAccess().getOpPercentSignEqualsSignKeyword_1_0_0_4());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getAssignmentStatementRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_2_5, null);
                            						

                            }
                            break;

                    }


                    }


                    }

                    // InternalL101.g:552:4: ( ( (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant ) ) )
                    // InternalL101.g:553:5: ( (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant ) )
                    {
                    // InternalL101.g:553:5: ( (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant ) )
                    // InternalL101.g:554:6: (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant )
                    {
                    // InternalL101.g:554:6: (lv_operand_3_1= ruleVariableOrUserInput | lv_operand_3_2= ruleCurrent | lv_operand_3_3= ruleConstant )
                    int alt7=3;
                    switch ( input.LA(1) ) {
                    case RULE_ID:
                    case 46:
                        {
                        alt7=1;
                        }
                        break;
                    case 45:
                        {
                        alt7=2;
                        }
                        break;
                    case RULE_NUMBER:
                    case 32:
                        {
                        alt7=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 0, input);

                        throw nvae;
                    }

                    switch (alt7) {
                        case 1 :
                            // InternalL101.g:555:7: lv_operand_3_1= ruleVariableOrUserInput
                            {

                            							newCompositeNode(grammarAccess.getAssignmentStatementAccess().getOperandVariableOrUserInputParserRuleCall_1_1_0_0());
                            						
                            pushFollow(FOLLOW_2);
                            lv_operand_3_1=ruleVariableOrUserInput();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getAssignmentStatementRule());
                            							}
                            							set(
                            								current,
                            								"operand",
                            								lv_operand_3_1,
                            								"org.mzattera.dsl.L101.VariableOrUserInput");
                            							afterParserOrEnumRuleCall();
                            						

                            }
                            break;
                        case 2 :
                            // InternalL101.g:571:7: lv_operand_3_2= ruleCurrent
                            {

                            							newCompositeNode(grammarAccess.getAssignmentStatementAccess().getOperandCurrentParserRuleCall_1_1_0_1());
                            						
                            pushFollow(FOLLOW_2);
                            lv_operand_3_2=ruleCurrent();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getAssignmentStatementRule());
                            							}
                            							set(
                            								current,
                            								"operand",
                            								lv_operand_3_2,
                            								"org.mzattera.dsl.L101.Current");
                            							afterParserOrEnumRuleCall();
                            						

                            }
                            break;
                        case 3 :
                            // InternalL101.g:587:7: lv_operand_3_3= ruleConstant
                            {

                            							newCompositeNode(grammarAccess.getAssignmentStatementAccess().getOperandConstantParserRuleCall_1_1_0_2());
                            						
                            pushFollow(FOLLOW_2);
                            lv_operand_3_3=ruleConstant();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getAssignmentStatementRule());
                            							}
                            							set(
                            								current,
                            								"operand",
                            								lv_operand_3_3,
                            								"org.mzattera.dsl.L101.Constant");
                            							afterParserOrEnumRuleCall();
                            						

                            }
                            break;

                    }


                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalL101.g:607:3: ( ( (lv_store_4_0= ruleVariableAccess ) ) ( (lv_op_5_0= '<->' ) ) ( (lv_operand_6_0= ruleExpression ) ) )
                    {
                    // InternalL101.g:607:3: ( ( (lv_store_4_0= ruleVariableAccess ) ) ( (lv_op_5_0= '<->' ) ) ( (lv_operand_6_0= ruleExpression ) ) )
                    // InternalL101.g:608:4: ( (lv_store_4_0= ruleVariableAccess ) ) ( (lv_op_5_0= '<->' ) ) ( (lv_operand_6_0= ruleExpression ) )
                    {
                    // InternalL101.g:608:4: ( (lv_store_4_0= ruleVariableAccess ) )
                    // InternalL101.g:609:5: (lv_store_4_0= ruleVariableAccess )
                    {
                    // InternalL101.g:609:5: (lv_store_4_0= ruleVariableAccess )
                    // InternalL101.g:610:6: lv_store_4_0= ruleVariableAccess
                    {

                    						newCompositeNode(grammarAccess.getAssignmentStatementAccess().getStoreVariableAccessParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_10);
                    lv_store_4_0=ruleVariableAccess();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAssignmentStatementRule());
                    						}
                    						set(
                    							current,
                    							"store",
                    							lv_store_4_0,
                    							"org.mzattera.dsl.L101.VariableAccess");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalL101.g:627:4: ( (lv_op_5_0= '<->' ) )
                    // InternalL101.g:628:5: (lv_op_5_0= '<->' )
                    {
                    // InternalL101.g:628:5: (lv_op_5_0= '<->' )
                    // InternalL101.g:629:6: lv_op_5_0= '<->'
                    {
                    lv_op_5_0=(Token)match(input,30,FOLLOW_8); 

                    						newLeafNode(lv_op_5_0, grammarAccess.getAssignmentStatementAccess().getOpLessThanSignHyphenMinusGreaterThanSignKeyword_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssignmentStatementRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_5_0, "<->");
                    					

                    }


                    }

                    // InternalL101.g:641:4: ( (lv_operand_6_0= ruleExpression ) )
                    // InternalL101.g:642:5: (lv_operand_6_0= ruleExpression )
                    {
                    // InternalL101.g:642:5: (lv_operand_6_0= ruleExpression )
                    // InternalL101.g:643:6: lv_operand_6_0= ruleExpression
                    {

                    						newCompositeNode(grammarAccess.getAssignmentStatementAccess().getOperandExpressionParserRuleCall_2_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_operand_6_0=ruleExpression();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAssignmentStatementRule());
                    						}
                    						set(
                    							current,
                    							"operand",
                    							lv_operand_6_0,
                    							"org.mzattera.dsl.L101.Expression");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssignmentStatement"


    // $ANTLR start "entryRuleExpression"
    // InternalL101.g:665:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalL101.g:665:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalL101.g:666:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalL101.g:672:1: ruleExpression returns [EObject current=null] : this_AdditiveOperator_0= ruleAdditiveOperator ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_AdditiveOperator_0 = null;



        	enterRule();

        try {
            // InternalL101.g:678:2: (this_AdditiveOperator_0= ruleAdditiveOperator )
            // InternalL101.g:679:2: this_AdditiveOperator_0= ruleAdditiveOperator
            {

            		newCompositeNode(grammarAccess.getExpressionAccess().getAdditiveOperatorParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_AdditiveOperator_0=ruleAdditiveOperator();

            state._fsp--;


            		current = this_AdditiveOperator_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleAdditiveOperator"
    // InternalL101.g:690:1: entryRuleAdditiveOperator returns [EObject current=null] : iv_ruleAdditiveOperator= ruleAdditiveOperator EOF ;
    public final EObject entryRuleAdditiveOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveOperator = null;


        try {
            // InternalL101.g:690:57: (iv_ruleAdditiveOperator= ruleAdditiveOperator EOF )
            // InternalL101.g:691:2: iv_ruleAdditiveOperator= ruleAdditiveOperator EOF
            {
             newCompositeNode(grammarAccess.getAdditiveOperatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAdditiveOperator=ruleAdditiveOperator();

            state._fsp--;

             current =iv_ruleAdditiveOperator; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAdditiveOperator"


    // $ANTLR start "ruleAdditiveOperator"
    // InternalL101.g:697:1: ruleAdditiveOperator returns [EObject current=null] : (this_MultiplicativeOperator_0= ruleMultiplicativeOperator ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )* ) ;
    public final EObject ruleAdditiveOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_MultiplicativeOperator_0 = null;

        EObject lv_right_3_1 = null;

        EObject lv_right_3_2 = null;



        	enterRule();

        try {
            // InternalL101.g:703:2: ( (this_MultiplicativeOperator_0= ruleMultiplicativeOperator ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )* ) )
            // InternalL101.g:704:2: (this_MultiplicativeOperator_0= ruleMultiplicativeOperator ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )* )
            {
            // InternalL101.g:704:2: (this_MultiplicativeOperator_0= ruleMultiplicativeOperator ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )* )
            // InternalL101.g:705:3: this_MultiplicativeOperator_0= ruleMultiplicativeOperator ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )*
            {

            			newCompositeNode(grammarAccess.getAdditiveOperatorAccess().getMultiplicativeOperatorParserRuleCall_0());
            		
            pushFollow(FOLLOW_11);
            this_MultiplicativeOperator_0=ruleMultiplicativeOperator();

            state._fsp--;


            			current = this_MultiplicativeOperator_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalL101.g:713:3: ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=31 && LA11_0<=32)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalL101.g:714:4: () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) )
            	    {
            	    // InternalL101.g:714:4: ()
            	    // InternalL101.g:715:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAdditiveOperatorAccess().getAdditiveOperatorLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalL101.g:721:4: ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
            	    // InternalL101.g:722:5: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    {
            	    // InternalL101.g:722:5: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    // InternalL101.g:723:6: (lv_op_2_1= '+' | lv_op_2_2= '-' )
            	    {
            	    // InternalL101.g:723:6: (lv_op_2_1= '+' | lv_op_2_2= '-' )
            	    int alt9=2;
            	    int LA9_0 = input.LA(1);

            	    if ( (LA9_0==31) ) {
            	        alt9=1;
            	    }
            	    else if ( (LA9_0==32) ) {
            	        alt9=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 9, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt9) {
            	        case 1 :
            	            // InternalL101.g:724:7: lv_op_2_1= '+'
            	            {
            	            lv_op_2_1=(Token)match(input,31,FOLLOW_12); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getAdditiveOperatorAccess().getOpPlusSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getAdditiveOperatorRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalL101.g:735:7: lv_op_2_2= '-'
            	            {
            	            lv_op_2_2=(Token)match(input,32,FOLLOW_12); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getAdditiveOperatorAccess().getOpHyphenMinusKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getAdditiveOperatorRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalL101.g:748:4: ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) )
            	    // InternalL101.g:749:5: ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) )
            	    {
            	    // InternalL101.g:749:5: ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) )
            	    // InternalL101.g:750:6: (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant )
            	    {
            	    // InternalL101.g:750:6: (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant )
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==RULE_ID||LA10_0==46) ) {
            	        alt10=1;
            	    }
            	    else if ( (LA10_0==RULE_NUMBER||LA10_0==32) ) {
            	        alt10=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 10, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // InternalL101.g:751:7: lv_right_3_1= ruleVariableOrUserInput
            	            {

            	            							newCompositeNode(grammarAccess.getAdditiveOperatorAccess().getRightVariableOrUserInputParserRuleCall_1_2_0_0());
            	            						
            	            pushFollow(FOLLOW_11);
            	            lv_right_3_1=ruleVariableOrUserInput();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getAdditiveOperatorRule());
            	            							}
            	            							set(
            	            								current,
            	            								"right",
            	            								lv_right_3_1,
            	            								"org.mzattera.dsl.L101.VariableOrUserInput");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalL101.g:767:7: lv_right_3_2= ruleConstant
            	            {

            	            							newCompositeNode(grammarAccess.getAdditiveOperatorAccess().getRightConstantParserRuleCall_1_2_0_1());
            	            						
            	            pushFollow(FOLLOW_11);
            	            lv_right_3_2=ruleConstant();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getAdditiveOperatorRule());
            	            							}
            	            							set(
            	            								current,
            	            								"right",
            	            								lv_right_3_2,
            	            								"org.mzattera.dsl.L101.Constant");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAdditiveOperator"


    // $ANTLR start "entryRuleMultiplicativeOperator"
    // InternalL101.g:790:1: entryRuleMultiplicativeOperator returns [EObject current=null] : iv_ruleMultiplicativeOperator= ruleMultiplicativeOperator EOF ;
    public final EObject entryRuleMultiplicativeOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeOperator = null;


        try {
            // InternalL101.g:790:63: (iv_ruleMultiplicativeOperator= ruleMultiplicativeOperator EOF )
            // InternalL101.g:791:2: iv_ruleMultiplicativeOperator= ruleMultiplicativeOperator EOF
            {
             newCompositeNode(grammarAccess.getMultiplicativeOperatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMultiplicativeOperator=ruleMultiplicativeOperator();

            state._fsp--;

             current =iv_ruleMultiplicativeOperator; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMultiplicativeOperator"


    // $ANTLR start "ruleMultiplicativeOperator"
    // InternalL101.g:797:1: ruleMultiplicativeOperator returns [EObject current=null] : (this_UnaryOperator_0= ruleUnaryOperator ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )* ) ;
    public final EObject ruleMultiplicativeOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        EObject this_UnaryOperator_0 = null;

        EObject lv_right_3_1 = null;

        EObject lv_right_3_2 = null;



        	enterRule();

        try {
            // InternalL101.g:803:2: ( (this_UnaryOperator_0= ruleUnaryOperator ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )* ) )
            // InternalL101.g:804:2: (this_UnaryOperator_0= ruleUnaryOperator ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )* )
            {
            // InternalL101.g:804:2: (this_UnaryOperator_0= ruleUnaryOperator ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )* )
            // InternalL101.g:805:3: this_UnaryOperator_0= ruleUnaryOperator ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )*
            {

            			newCompositeNode(grammarAccess.getMultiplicativeOperatorAccess().getUnaryOperatorParserRuleCall_0());
            		
            pushFollow(FOLLOW_13);
            this_UnaryOperator_0=ruleUnaryOperator();

            state._fsp--;


            			current = this_UnaryOperator_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalL101.g:813:3: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=33 && LA14_0<=35)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalL101.g:814:4: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) )
            	    {
            	    // InternalL101.g:814:4: ()
            	    // InternalL101.g:815:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getMultiplicativeOperatorAccess().getMultiplicativeOperatorLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalL101.g:821:4: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) )
            	    // InternalL101.g:822:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) )
            	    {
            	    // InternalL101.g:822:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) )
            	    // InternalL101.g:823:6: (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' )
            	    {
            	    // InternalL101.g:823:6: (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' )
            	    int alt12=3;
            	    switch ( input.LA(1) ) {
            	    case 33:
            	        {
            	        alt12=1;
            	        }
            	        break;
            	    case 34:
            	        {
            	        alt12=2;
            	        }
            	        break;
            	    case 35:
            	        {
            	        alt12=3;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 12, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt12) {
            	        case 1 :
            	            // InternalL101.g:824:7: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,33,FOLLOW_12); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getMultiplicativeOperatorAccess().getOpAsteriskKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getMultiplicativeOperatorRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalL101.g:835:7: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,34,FOLLOW_12); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getMultiplicativeOperatorAccess().getOpSolidusKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getMultiplicativeOperatorRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;
            	        case 3 :
            	            // InternalL101.g:846:7: lv_op_2_3= '%'
            	            {
            	            lv_op_2_3=(Token)match(input,35,FOLLOW_12); 

            	            							newLeafNode(lv_op_2_3, grammarAccess.getMultiplicativeOperatorAccess().getOpPercentSignKeyword_1_1_0_2());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getMultiplicativeOperatorRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_3, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalL101.g:859:4: ( ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) ) )
            	    // InternalL101.g:860:5: ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) )
            	    {
            	    // InternalL101.g:860:5: ( (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant ) )
            	    // InternalL101.g:861:6: (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant )
            	    {
            	    // InternalL101.g:861:6: (lv_right_3_1= ruleVariableOrUserInput | lv_right_3_2= ruleConstant )
            	    int alt13=2;
            	    int LA13_0 = input.LA(1);

            	    if ( (LA13_0==RULE_ID||LA13_0==46) ) {
            	        alt13=1;
            	    }
            	    else if ( (LA13_0==RULE_NUMBER||LA13_0==32) ) {
            	        alt13=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 13, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt13) {
            	        case 1 :
            	            // InternalL101.g:862:7: lv_right_3_1= ruleVariableOrUserInput
            	            {

            	            							newCompositeNode(grammarAccess.getMultiplicativeOperatorAccess().getRightVariableOrUserInputParserRuleCall_1_2_0_0());
            	            						
            	            pushFollow(FOLLOW_13);
            	            lv_right_3_1=ruleVariableOrUserInput();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getMultiplicativeOperatorRule());
            	            							}
            	            							set(
            	            								current,
            	            								"right",
            	            								lv_right_3_1,
            	            								"org.mzattera.dsl.L101.VariableOrUserInput");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalL101.g:878:7: lv_right_3_2= ruleConstant
            	            {

            	            							newCompositeNode(grammarAccess.getMultiplicativeOperatorAccess().getRightConstantParserRuleCall_1_2_0_1());
            	            						
            	            pushFollow(FOLLOW_13);
            	            lv_right_3_2=ruleConstant();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getMultiplicativeOperatorRule());
            	            							}
            	            							set(
            	            								current,
            	            								"right",
            	            								lv_right_3_2,
            	            								"org.mzattera.dsl.L101.Constant");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMultiplicativeOperator"


    // $ANTLR start "entryRuleUnaryOperator"
    // InternalL101.g:901:1: entryRuleUnaryOperator returns [EObject current=null] : iv_ruleUnaryOperator= ruleUnaryOperator EOF ;
    public final EObject entryRuleUnaryOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOperator = null;


        try {
            // InternalL101.g:901:54: (iv_ruleUnaryOperator= ruleUnaryOperator EOF )
            // InternalL101.g:902:2: iv_ruleUnaryOperator= ruleUnaryOperator EOF
            {
             newCompositeNode(grammarAccess.getUnaryOperatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnaryOperator=ruleUnaryOperator();

            state._fsp--;

             current =iv_ruleUnaryOperator; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnaryOperator"


    // $ANTLR start "ruleUnaryOperator"
    // InternalL101.g:908:1: ruleUnaryOperator returns [EObject current=null] : ( ( () ( ( (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' ) ) ) ( (lv_operand_2_0= ruleUnaryOperator ) ) ) | this_Atomic_3= ruleAtomic ) ;
    public final EObject ruleUnaryOperator() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        Token lv_op_1_3=null;
        Token lv_op_1_4=null;
        Token lv_op_1_5=null;
        Token lv_op_1_6=null;
        Token lv_op_1_7=null;
        EObject lv_operand_2_0 = null;

        EObject this_Atomic_3 = null;



        	enterRule();

        try {
            // InternalL101.g:914:2: ( ( ( () ( ( (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' ) ) ) ( (lv_operand_2_0= ruleUnaryOperator ) ) ) | this_Atomic_3= ruleAtomic ) )
            // InternalL101.g:915:2: ( ( () ( ( (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' ) ) ) ( (lv_operand_2_0= ruleUnaryOperator ) ) ) | this_Atomic_3= ruleAtomic )
            {
            // InternalL101.g:915:2: ( ( () ( ( (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' ) ) ) ( (lv_operand_2_0= ruleUnaryOperator ) ) ) | this_Atomic_3= ruleAtomic )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=36 && LA16_0<=42)) ) {
                alt16=1;
            }
            else if ( ((LA16_0>=RULE_ID && LA16_0<=RULE_NUMBER)||LA16_0==32||LA16_0==43||(LA16_0>=45 && LA16_0<=46)) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // InternalL101.g:916:3: ( () ( ( (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' ) ) ) ( (lv_operand_2_0= ruleUnaryOperator ) ) )
                    {
                    // InternalL101.g:916:3: ( () ( ( (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' ) ) ) ( (lv_operand_2_0= ruleUnaryOperator ) ) )
                    // InternalL101.g:917:4: () ( ( (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' ) ) ) ( (lv_operand_2_0= ruleUnaryOperator ) )
                    {
                    // InternalL101.g:917:4: ()
                    // InternalL101.g:918:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getUnaryOperatorAccess().getUnaryOperatorAction_0_0(),
                    						current);
                    				

                    }

                    // InternalL101.g:924:4: ( ( (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' ) ) )
                    // InternalL101.g:925:5: ( (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' ) )
                    {
                    // InternalL101.g:925:5: ( (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' ) )
                    // InternalL101.g:926:6: (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' )
                    {
                    // InternalL101.g:926:6: (lv_op_1_1= 'SQ' | lv_op_1_2= 'SQRT' | lv_op_1_3= 'INT' | lv_op_1_4= 'DEC' | lv_op_1_5= 'INV' | lv_op_1_6= 'NEG' | lv_op_1_7= 'ABS' )
                    int alt15=7;
                    switch ( input.LA(1) ) {
                    case 36:
                        {
                        alt15=1;
                        }
                        break;
                    case 37:
                        {
                        alt15=2;
                        }
                        break;
                    case 38:
                        {
                        alt15=3;
                        }
                        break;
                    case 39:
                        {
                        alt15=4;
                        }
                        break;
                    case 40:
                        {
                        alt15=5;
                        }
                        break;
                    case 41:
                        {
                        alt15=6;
                        }
                        break;
                    case 42:
                        {
                        alt15=7;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 15, 0, input);

                        throw nvae;
                    }

                    switch (alt15) {
                        case 1 :
                            // InternalL101.g:927:7: lv_op_1_1= 'SQ'
                            {
                            lv_op_1_1=(Token)match(input,36,FOLLOW_8); 

                            							newLeafNode(lv_op_1_1, grammarAccess.getUnaryOperatorAccess().getOpSQKeyword_0_1_0_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getUnaryOperatorRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_1_1, null);
                            						

                            }
                            break;
                        case 2 :
                            // InternalL101.g:938:7: lv_op_1_2= 'SQRT'
                            {
                            lv_op_1_2=(Token)match(input,37,FOLLOW_8); 

                            							newLeafNode(lv_op_1_2, grammarAccess.getUnaryOperatorAccess().getOpSQRTKeyword_0_1_0_1());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getUnaryOperatorRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_1_2, null);
                            						

                            }
                            break;
                        case 3 :
                            // InternalL101.g:949:7: lv_op_1_3= 'INT'
                            {
                            lv_op_1_3=(Token)match(input,38,FOLLOW_8); 

                            							newLeafNode(lv_op_1_3, grammarAccess.getUnaryOperatorAccess().getOpINTKeyword_0_1_0_2());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getUnaryOperatorRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_1_3, null);
                            						

                            }
                            break;
                        case 4 :
                            // InternalL101.g:960:7: lv_op_1_4= 'DEC'
                            {
                            lv_op_1_4=(Token)match(input,39,FOLLOW_8); 

                            							newLeafNode(lv_op_1_4, grammarAccess.getUnaryOperatorAccess().getOpDECKeyword_0_1_0_3());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getUnaryOperatorRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_1_4, null);
                            						

                            }
                            break;
                        case 5 :
                            // InternalL101.g:971:7: lv_op_1_5= 'INV'
                            {
                            lv_op_1_5=(Token)match(input,40,FOLLOW_8); 

                            							newLeafNode(lv_op_1_5, grammarAccess.getUnaryOperatorAccess().getOpINVKeyword_0_1_0_4());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getUnaryOperatorRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_1_5, null);
                            						

                            }
                            break;
                        case 6 :
                            // InternalL101.g:982:7: lv_op_1_6= 'NEG'
                            {
                            lv_op_1_6=(Token)match(input,41,FOLLOW_8); 

                            							newLeafNode(lv_op_1_6, grammarAccess.getUnaryOperatorAccess().getOpNEGKeyword_0_1_0_5());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getUnaryOperatorRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_1_6, null);
                            						

                            }
                            break;
                        case 7 :
                            // InternalL101.g:993:7: lv_op_1_7= 'ABS'
                            {
                            lv_op_1_7=(Token)match(input,42,FOLLOW_8); 

                            							newLeafNode(lv_op_1_7, grammarAccess.getUnaryOperatorAccess().getOpABSKeyword_0_1_0_6());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getUnaryOperatorRule());
                            							}
                            							setWithLastConsumed(current, "op", lv_op_1_7, null);
                            						

                            }
                            break;

                    }


                    }


                    }

                    // InternalL101.g:1006:4: ( (lv_operand_2_0= ruleUnaryOperator ) )
                    // InternalL101.g:1007:5: (lv_operand_2_0= ruleUnaryOperator )
                    {
                    // InternalL101.g:1007:5: (lv_operand_2_0= ruleUnaryOperator )
                    // InternalL101.g:1008:6: lv_operand_2_0= ruleUnaryOperator
                    {

                    						newCompositeNode(grammarAccess.getUnaryOperatorAccess().getOperandUnaryOperatorParserRuleCall_0_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_operand_2_0=ruleUnaryOperator();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getUnaryOperatorRule());
                    						}
                    						set(
                    							current,
                    							"operand",
                    							lv_operand_2_0,
                    							"org.mzattera.dsl.L101.UnaryOperator");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalL101.g:1027:3: this_Atomic_3= ruleAtomic
                    {

                    			newCompositeNode(grammarAccess.getUnaryOperatorAccess().getAtomicParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Atomic_3=ruleAtomic();

                    state._fsp--;


                    			current = this_Atomic_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnaryOperator"


    // $ANTLR start "entryRuleAtomic"
    // InternalL101.g:1039:1: entryRuleAtomic returns [EObject current=null] : iv_ruleAtomic= ruleAtomic EOF ;
    public final EObject entryRuleAtomic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomic = null;


        try {
            // InternalL101.g:1039:47: (iv_ruleAtomic= ruleAtomic EOF )
            // InternalL101.g:1040:2: iv_ruleAtomic= ruleAtomic EOF
            {
             newCompositeNode(grammarAccess.getAtomicRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAtomic=ruleAtomic();

            state._fsp--;

             current =iv_ruleAtomic; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAtomic"


    // $ANTLR start "ruleAtomic"
    // InternalL101.g:1046:1: ruleAtomic returns [EObject current=null] : (this_VariableAccess_0= ruleVariableAccess | this_Current_1= ruleCurrent | this_UserInput_2= ruleUserInput | this_Constant_3= ruleConstant | (otherlv_4= '(' this_Expression_5= ruleExpression otherlv_6= ')' ) ) ;
    public final EObject ruleAtomic() throws RecognitionException {
        EObject current = null;

        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject this_VariableAccess_0 = null;

        EObject this_Current_1 = null;

        EObject this_UserInput_2 = null;

        EObject this_Constant_3 = null;

        EObject this_Expression_5 = null;



        	enterRule();

        try {
            // InternalL101.g:1052:2: ( (this_VariableAccess_0= ruleVariableAccess | this_Current_1= ruleCurrent | this_UserInput_2= ruleUserInput | this_Constant_3= ruleConstant | (otherlv_4= '(' this_Expression_5= ruleExpression otherlv_6= ')' ) ) )
            // InternalL101.g:1053:2: (this_VariableAccess_0= ruleVariableAccess | this_Current_1= ruleCurrent | this_UserInput_2= ruleUserInput | this_Constant_3= ruleConstant | (otherlv_4= '(' this_Expression_5= ruleExpression otherlv_6= ')' ) )
            {
            // InternalL101.g:1053:2: (this_VariableAccess_0= ruleVariableAccess | this_Current_1= ruleCurrent | this_UserInput_2= ruleUserInput | this_Constant_3= ruleConstant | (otherlv_4= '(' this_Expression_5= ruleExpression otherlv_6= ')' ) )
            int alt17=5;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt17=1;
                }
                break;
            case 45:
                {
                alt17=2;
                }
                break;
            case 46:
                {
                alt17=3;
                }
                break;
            case RULE_NUMBER:
            case 32:
                {
                alt17=4;
                }
                break;
            case 43:
                {
                alt17=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // InternalL101.g:1054:3: this_VariableAccess_0= ruleVariableAccess
                    {

                    			newCompositeNode(grammarAccess.getAtomicAccess().getVariableAccessParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_VariableAccess_0=ruleVariableAccess();

                    state._fsp--;


                    			current = this_VariableAccess_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalL101.g:1063:3: this_Current_1= ruleCurrent
                    {

                    			newCompositeNode(grammarAccess.getAtomicAccess().getCurrentParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Current_1=ruleCurrent();

                    state._fsp--;


                    			current = this_Current_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalL101.g:1072:3: this_UserInput_2= ruleUserInput
                    {

                    			newCompositeNode(grammarAccess.getAtomicAccess().getUserInputParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_UserInput_2=ruleUserInput();

                    state._fsp--;


                    			current = this_UserInput_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalL101.g:1081:3: this_Constant_3= ruleConstant
                    {

                    			newCompositeNode(grammarAccess.getAtomicAccess().getConstantParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Constant_3=ruleConstant();

                    state._fsp--;


                    			current = this_Constant_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalL101.g:1090:3: (otherlv_4= '(' this_Expression_5= ruleExpression otherlv_6= ')' )
                    {
                    // InternalL101.g:1090:3: (otherlv_4= '(' this_Expression_5= ruleExpression otherlv_6= ')' )
                    // InternalL101.g:1091:4: otherlv_4= '(' this_Expression_5= ruleExpression otherlv_6= ')'
                    {
                    otherlv_4=(Token)match(input,43,FOLLOW_8); 

                    				newLeafNode(otherlv_4, grammarAccess.getAtomicAccess().getLeftParenthesisKeyword_4_0());
                    			

                    				newCompositeNode(grammarAccess.getAtomicAccess().getExpressionParserRuleCall_4_1());
                    			
                    pushFollow(FOLLOW_14);
                    this_Expression_5=ruleExpression();

                    state._fsp--;


                    				current = this_Expression_5;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_6=(Token)match(input,44,FOLLOW_2); 

                    				newLeafNode(otherlv_6, grammarAccess.getAtomicAccess().getRightParenthesisKeyword_4_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAtomic"


    // $ANTLR start "entryRuleCurrent"
    // InternalL101.g:1112:1: entryRuleCurrent returns [EObject current=null] : iv_ruleCurrent= ruleCurrent EOF ;
    public final EObject entryRuleCurrent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCurrent = null;


        try {
            // InternalL101.g:1112:48: (iv_ruleCurrent= ruleCurrent EOF )
            // InternalL101.g:1113:2: iv_ruleCurrent= ruleCurrent EOF
            {
             newCompositeNode(grammarAccess.getCurrentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCurrent=ruleCurrent();

            state._fsp--;

             current =iv_ruleCurrent; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCurrent"


    // $ANTLR start "ruleCurrent"
    // InternalL101.g:1119:1: ruleCurrent returns [EObject current=null] : ( () otherlv_1= 'A' ) ;
    public final EObject ruleCurrent() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalL101.g:1125:2: ( ( () otherlv_1= 'A' ) )
            // InternalL101.g:1126:2: ( () otherlv_1= 'A' )
            {
            // InternalL101.g:1126:2: ( () otherlv_1= 'A' )
            // InternalL101.g:1127:3: () otherlv_1= 'A'
            {
            // InternalL101.g:1127:3: ()
            // InternalL101.g:1128:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getCurrentAccess().getCurrentAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,45,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getCurrentAccess().getAKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCurrent"


    // $ANTLR start "entryRuleUserInput"
    // InternalL101.g:1142:1: entryRuleUserInput returns [EObject current=null] : iv_ruleUserInput= ruleUserInput EOF ;
    public final EObject entryRuleUserInput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUserInput = null;


        try {
            // InternalL101.g:1142:50: (iv_ruleUserInput= ruleUserInput EOF )
            // InternalL101.g:1143:2: iv_ruleUserInput= ruleUserInput EOF
            {
             newCompositeNode(grammarAccess.getUserInputRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUserInput=ruleUserInput();

            state._fsp--;

             current =iv_ruleUserInput; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUserInput"


    // $ANTLR start "ruleUserInput"
    // InternalL101.g:1149:1: ruleUserInput returns [EObject current=null] : ( () otherlv_1= 'INPUT' ) ;
    public final EObject ruleUserInput() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalL101.g:1155:2: ( ( () otherlv_1= 'INPUT' ) )
            // InternalL101.g:1156:2: ( () otherlv_1= 'INPUT' )
            {
            // InternalL101.g:1156:2: ( () otherlv_1= 'INPUT' )
            // InternalL101.g:1157:3: () otherlv_1= 'INPUT'
            {
            // InternalL101.g:1157:3: ()
            // InternalL101.g:1158:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getUserInputAccess().getInputAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,46,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getUserInputAccess().getINPUTKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUserInput"


    // $ANTLR start "entryRuleConstant"
    // InternalL101.g:1172:1: entryRuleConstant returns [EObject current=null] : iv_ruleConstant= ruleConstant EOF ;
    public final EObject entryRuleConstant() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstant = null;


        try {
            // InternalL101.g:1172:49: (iv_ruleConstant= ruleConstant EOF )
            // InternalL101.g:1173:2: iv_ruleConstant= ruleConstant EOF
            {
             newCompositeNode(grammarAccess.getConstantRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConstant=ruleConstant();

            state._fsp--;

             current =iv_ruleConstant; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstant"


    // $ANTLR start "ruleConstant"
    // InternalL101.g:1179:1: ruleConstant returns [EObject current=null] : ( () ( (lv_negative_1_0= '-' ) )? ( (lv_value_2_0= RULE_NUMBER ) ) ) ;
    public final EObject ruleConstant() throws RecognitionException {
        EObject current = null;

        Token lv_negative_1_0=null;
        Token lv_value_2_0=null;


        	enterRule();

        try {
            // InternalL101.g:1185:2: ( ( () ( (lv_negative_1_0= '-' ) )? ( (lv_value_2_0= RULE_NUMBER ) ) ) )
            // InternalL101.g:1186:2: ( () ( (lv_negative_1_0= '-' ) )? ( (lv_value_2_0= RULE_NUMBER ) ) )
            {
            // InternalL101.g:1186:2: ( () ( (lv_negative_1_0= '-' ) )? ( (lv_value_2_0= RULE_NUMBER ) ) )
            // InternalL101.g:1187:3: () ( (lv_negative_1_0= '-' ) )? ( (lv_value_2_0= RULE_NUMBER ) )
            {
            // InternalL101.g:1187:3: ()
            // InternalL101.g:1188:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getConstantAccess().getConstantAction_0(),
            					current);
            			

            }

            // InternalL101.g:1194:3: ( (lv_negative_1_0= '-' ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==32) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalL101.g:1195:4: (lv_negative_1_0= '-' )
                    {
                    // InternalL101.g:1195:4: (lv_negative_1_0= '-' )
                    // InternalL101.g:1196:5: lv_negative_1_0= '-'
                    {
                    lv_negative_1_0=(Token)match(input,32,FOLLOW_15); 

                    					newLeafNode(lv_negative_1_0, grammarAccess.getConstantAccess().getNegativeHyphenMinusKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getConstantRule());
                    					}
                    					setWithLastConsumed(current, "negative", lv_negative_1_0 != null, "-");
                    				

                    }


                    }
                    break;

            }

            // InternalL101.g:1208:3: ( (lv_value_2_0= RULE_NUMBER ) )
            // InternalL101.g:1209:4: (lv_value_2_0= RULE_NUMBER )
            {
            // InternalL101.g:1209:4: (lv_value_2_0= RULE_NUMBER )
            // InternalL101.g:1210:5: lv_value_2_0= RULE_NUMBER
            {
            lv_value_2_0=(Token)match(input,RULE_NUMBER,FOLLOW_2); 

            					newLeafNode(lv_value_2_0, grammarAccess.getConstantAccess().getValueNUMBERTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConstantRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_2_0,
            						"org.mzattera.dsl.L101.NUMBER");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstant"


    // $ANTLR start "entryRuleStatements"
    // InternalL101.g:1230:1: entryRuleStatements returns [EObject current=null] : iv_ruleStatements= ruleStatements EOF ;
    public final EObject entryRuleStatements() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStatements = null;


        try {
            // InternalL101.g:1230:51: (iv_ruleStatements= ruleStatements EOF )
            // InternalL101.g:1231:2: iv_ruleStatements= ruleStatements EOF
            {
             newCompositeNode(grammarAccess.getStatementsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStatements=ruleStatements();

            state._fsp--;

             current =iv_ruleStatements; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStatements"


    // $ANTLR start "ruleStatements"
    // InternalL101.g:1237:1: ruleStatements returns [EObject current=null] : ( () ( (lv_statements_1_0= ruleStatement ) )* ) ;
    public final EObject ruleStatements() throws RecognitionException {
        EObject current = null;

        EObject lv_statements_1_0 = null;



        	enterRule();

        try {
            // InternalL101.g:1243:2: ( ( () ( (lv_statements_1_0= ruleStatement ) )* ) )
            // InternalL101.g:1244:2: ( () ( (lv_statements_1_0= ruleStatement ) )* )
            {
            // InternalL101.g:1244:2: ( () ( (lv_statements_1_0= ruleStatement ) )* )
            // InternalL101.g:1245:3: () ( (lv_statements_1_0= ruleStatement ) )*
            {
            // InternalL101.g:1245:3: ()
            // InternalL101.g:1246:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getStatementsAccess().getStatementsAction_0(),
            					current);
            			

            }

            // InternalL101.g:1252:3: ( (lv_statements_1_0= ruleStatement ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=RULE_ID && LA19_0<=RULE_NUMBER)||(LA19_0>=24 && LA19_0<=29)||LA19_0==32||LA19_0==46||LA19_0==54||(LA19_0>=56 && LA19_0<=59)||(LA19_0>=63 && LA19_0<=65)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalL101.g:1253:4: (lv_statements_1_0= ruleStatement )
            	    {
            	    // InternalL101.g:1253:4: (lv_statements_1_0= ruleStatement )
            	    // InternalL101.g:1254:5: lv_statements_1_0= ruleStatement
            	    {

            	    					newCompositeNode(grammarAccess.getStatementsAccess().getStatementsStatementParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_16);
            	    lv_statements_1_0=ruleStatement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getStatementsRule());
            	    					}
            	    					add(
            	    						current,
            	    						"statements",
            	    						lv_statements_1_0,
            	    						"org.mzattera.dsl.L101.Statement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStatements"


    // $ANTLR start "entryRuleStatement"
    // InternalL101.g:1275:1: entryRuleStatement returns [EObject current=null] : iv_ruleStatement= ruleStatement EOF ;
    public final EObject entryRuleStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStatement = null;


        try {
            // InternalL101.g:1275:50: (iv_ruleStatement= ruleStatement EOF )
            // InternalL101.g:1276:2: iv_ruleStatement= ruleStatement EOF
            {
             newCompositeNode(grammarAccess.getStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStatement=ruleStatement();

            state._fsp--;

             current =iv_ruleStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStatement"


    // $ANTLR start "ruleStatement"
    // InternalL101.g:1282:1: ruleStatement returns [EObject current=null] : (this_Label_0= ruleLabel | (this_AssignmentStatement_1= ruleAssignmentStatement otherlv_2= ';' ) | (this_SimpleStatement_3= ruleSimpleStatement otherlv_4= ';' ) | (this_SimpleControlStatement_5= ruleSimpleControlStatement otherlv_6= ';' ) | this_StructuredControlStatement_7= ruleStructuredControlStatement ) ;
    public final EObject ruleStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject this_Label_0 = null;

        EObject this_AssignmentStatement_1 = null;

        EObject this_SimpleStatement_3 = null;

        EObject this_SimpleControlStatement_5 = null;

        EObject this_StructuredControlStatement_7 = null;



        	enterRule();

        try {
            // InternalL101.g:1288:2: ( (this_Label_0= ruleLabel | (this_AssignmentStatement_1= ruleAssignmentStatement otherlv_2= ';' ) | (this_SimpleStatement_3= ruleSimpleStatement otherlv_4= ';' ) | (this_SimpleControlStatement_5= ruleSimpleControlStatement otherlv_6= ';' ) | this_StructuredControlStatement_7= ruleStructuredControlStatement ) )
            // InternalL101.g:1289:2: (this_Label_0= ruleLabel | (this_AssignmentStatement_1= ruleAssignmentStatement otherlv_2= ';' ) | (this_SimpleStatement_3= ruleSimpleStatement otherlv_4= ';' ) | (this_SimpleControlStatement_5= ruleSimpleControlStatement otherlv_6= ';' ) | this_StructuredControlStatement_7= ruleStructuredControlStatement )
            {
            // InternalL101.g:1289:2: (this_Label_0= ruleLabel | (this_AssignmentStatement_1= ruleAssignmentStatement otherlv_2= ';' ) | (this_SimpleStatement_3= ruleSimpleStatement otherlv_4= ';' ) | (this_SimpleControlStatement_5= ruleSimpleControlStatement otherlv_6= ';' ) | this_StructuredControlStatement_7= ruleStructuredControlStatement )
            int alt20=5;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==30) ) {
                    alt20=2;
                }
                else if ( (LA20_1==47||LA20_1==52) ) {
                    alt20=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;
                }
                }
                break;
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                {
                alt20=2;
                }
                break;
            case RULE_NUMBER:
            case 32:
            case 46:
            case 54:
            case 56:
                {
                alt20=3;
                }
                break;
            case 57:
            case 58:
                {
                alt20=4;
                }
                break;
            case 59:
            case 63:
            case 64:
            case 65:
                {
                alt20=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // InternalL101.g:1290:3: this_Label_0= ruleLabel
                    {

                    			newCompositeNode(grammarAccess.getStatementAccess().getLabelParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Label_0=ruleLabel();

                    state._fsp--;


                    			current = this_Label_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalL101.g:1299:3: (this_AssignmentStatement_1= ruleAssignmentStatement otherlv_2= ';' )
                    {
                    // InternalL101.g:1299:3: (this_AssignmentStatement_1= ruleAssignmentStatement otherlv_2= ';' )
                    // InternalL101.g:1300:4: this_AssignmentStatement_1= ruleAssignmentStatement otherlv_2= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getAssignmentStatementParserRuleCall_1_0());
                    			
                    pushFollow(FOLLOW_17);
                    this_AssignmentStatement_1=ruleAssignmentStatement();

                    state._fsp--;


                    				current = this_AssignmentStatement_1;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_2=(Token)match(input,12,FOLLOW_2); 

                    				newLeafNode(otherlv_2, grammarAccess.getStatementAccess().getSemicolonKeyword_1_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalL101.g:1314:3: (this_SimpleStatement_3= ruleSimpleStatement otherlv_4= ';' )
                    {
                    // InternalL101.g:1314:3: (this_SimpleStatement_3= ruleSimpleStatement otherlv_4= ';' )
                    // InternalL101.g:1315:4: this_SimpleStatement_3= ruleSimpleStatement otherlv_4= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getSimpleStatementParserRuleCall_2_0());
                    			
                    pushFollow(FOLLOW_17);
                    this_SimpleStatement_3=ruleSimpleStatement();

                    state._fsp--;


                    				current = this_SimpleStatement_3;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_4=(Token)match(input,12,FOLLOW_2); 

                    				newLeafNode(otherlv_4, grammarAccess.getStatementAccess().getSemicolonKeyword_2_1());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalL101.g:1329:3: (this_SimpleControlStatement_5= ruleSimpleControlStatement otherlv_6= ';' )
                    {
                    // InternalL101.g:1329:3: (this_SimpleControlStatement_5= ruleSimpleControlStatement otherlv_6= ';' )
                    // InternalL101.g:1330:4: this_SimpleControlStatement_5= ruleSimpleControlStatement otherlv_6= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getSimpleControlStatementParserRuleCall_3_0());
                    			
                    pushFollow(FOLLOW_17);
                    this_SimpleControlStatement_5=ruleSimpleControlStatement();

                    state._fsp--;


                    				current = this_SimpleControlStatement_5;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_6=(Token)match(input,12,FOLLOW_2); 

                    				newLeafNode(otherlv_6, grammarAccess.getStatementAccess().getSemicolonKeyword_3_1());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalL101.g:1344:3: this_StructuredControlStatement_7= ruleStructuredControlStatement
                    {

                    			newCompositeNode(grammarAccess.getStatementAccess().getStructuredControlStatementParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_StructuredControlStatement_7=ruleStructuredControlStatement();

                    state._fsp--;


                    			current = this_StructuredControlStatement_7;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStatement"


    // $ANTLR start "entryRuleLabel"
    // InternalL101.g:1356:1: entryRuleLabel returns [EObject current=null] : iv_ruleLabel= ruleLabel EOF ;
    public final EObject entryRuleLabel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLabel = null;


        try {
            // InternalL101.g:1356:46: (iv_ruleLabel= ruleLabel EOF )
            // InternalL101.g:1357:2: iv_ruleLabel= ruleLabel EOF
            {
             newCompositeNode(grammarAccess.getLabelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLabel=ruleLabel();

            state._fsp--;

             current =iv_ruleLabel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLabel"


    // $ANTLR start "ruleLabel"
    // InternalL101.g:1363:1: ruleLabel returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= 'ON' ( ( (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' ) ) ) )? otherlv_3= ':' ) ;
    public final EObject ruleLabel() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_target_2_1=null;
        Token lv_target_2_2=null;
        Token lv_target_2_3=null;
        Token lv_target_2_4=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalL101.g:1369:2: ( ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= 'ON' ( ( (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' ) ) ) )? otherlv_3= ':' ) )
            // InternalL101.g:1370:2: ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= 'ON' ( ( (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' ) ) ) )? otherlv_3= ':' )
            {
            // InternalL101.g:1370:2: ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= 'ON' ( ( (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' ) ) ) )? otherlv_3= ':' )
            // InternalL101.g:1371:3: ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= 'ON' ( ( (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' ) ) ) )? otherlv_3= ':'
            {
            // InternalL101.g:1371:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalL101.g:1372:4: (lv_name_0_0= RULE_ID )
            {
            // InternalL101.g:1372:4: (lv_name_0_0= RULE_ID )
            // InternalL101.g:1373:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_18); 

            					newLeafNode(lv_name_0_0, grammarAccess.getLabelAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getLabelRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.mzattera.dsl.L101.ID");
            				

            }


            }

            // InternalL101.g:1389:3: (otherlv_1= 'ON' ( ( (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' ) ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==47) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalL101.g:1390:4: otherlv_1= 'ON' ( ( (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' ) ) )
                    {
                    otherlv_1=(Token)match(input,47,FOLLOW_19); 

                    				newLeafNode(otherlv_1, grammarAccess.getLabelAccess().getONKeyword_1_0());
                    			
                    // InternalL101.g:1394:4: ( ( (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' ) ) )
                    // InternalL101.g:1395:5: ( (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' ) )
                    {
                    // InternalL101.g:1395:5: ( (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' ) )
                    // InternalL101.g:1396:6: (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' )
                    {
                    // InternalL101.g:1396:6: (lv_target_2_1= 'V' | lv_target_2_2= 'W' | lv_target_2_3= 'Y' | lv_target_2_4= 'Z' )
                    int alt21=4;
                    switch ( input.LA(1) ) {
                    case 48:
                        {
                        alt21=1;
                        }
                        break;
                    case 49:
                        {
                        alt21=2;
                        }
                        break;
                    case 50:
                        {
                        alt21=3;
                        }
                        break;
                    case 51:
                        {
                        alt21=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 21, 0, input);

                        throw nvae;
                    }

                    switch (alt21) {
                        case 1 :
                            // InternalL101.g:1397:7: lv_target_2_1= 'V'
                            {
                            lv_target_2_1=(Token)match(input,48,FOLLOW_20); 

                            							newLeafNode(lv_target_2_1, grammarAccess.getLabelAccess().getTargetVKeyword_1_1_0_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getLabelRule());
                            							}
                            							setWithLastConsumed(current, "target", lv_target_2_1, null);
                            						

                            }
                            break;
                        case 2 :
                            // InternalL101.g:1408:7: lv_target_2_2= 'W'
                            {
                            lv_target_2_2=(Token)match(input,49,FOLLOW_20); 

                            							newLeafNode(lv_target_2_2, grammarAccess.getLabelAccess().getTargetWKeyword_1_1_0_1());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getLabelRule());
                            							}
                            							setWithLastConsumed(current, "target", lv_target_2_2, null);
                            						

                            }
                            break;
                        case 3 :
                            // InternalL101.g:1419:7: lv_target_2_3= 'Y'
                            {
                            lv_target_2_3=(Token)match(input,50,FOLLOW_20); 

                            							newLeafNode(lv_target_2_3, grammarAccess.getLabelAccess().getTargetYKeyword_1_1_0_2());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getLabelRule());
                            							}
                            							setWithLastConsumed(current, "target", lv_target_2_3, null);
                            						

                            }
                            break;
                        case 4 :
                            // InternalL101.g:1430:7: lv_target_2_4= 'Z'
                            {
                            lv_target_2_4=(Token)match(input,51,FOLLOW_20); 

                            							newLeafNode(lv_target_2_4, grammarAccess.getLabelAccess().getTargetZKeyword_1_1_0_3());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getLabelRule());
                            							}
                            							setWithLastConsumed(current, "target", lv_target_2_4, null);
                            						

                            }
                            break;

                    }


                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,52,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getLabelAccess().getColonKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLabel"


    // $ANTLR start "entryRuleSimpleStatement"
    // InternalL101.g:1452:1: entryRuleSimpleStatement returns [EObject current=null] : iv_ruleSimpleStatement= ruleSimpleStatement EOF ;
    public final EObject entryRuleSimpleStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleStatement = null;


        try {
            // InternalL101.g:1452:56: (iv_ruleSimpleStatement= ruleSimpleStatement EOF )
            // InternalL101.g:1453:2: iv_ruleSimpleStatement= ruleSimpleStatement EOF
            {
             newCompositeNode(grammarAccess.getSimpleStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleStatement=ruleSimpleStatement();

            state._fsp--;

             current =iv_ruleSimpleStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleStatement"


    // $ANTLR start "ruleSimpleStatement"
    // InternalL101.g:1459:1: ruleSimpleStatement returns [EObject current=null] : (this_StoreStatement_0= ruleStoreStatement | this_PrintStatement_1= rulePrintStatement | this_GotoStatement_2= ruleGotoStatement ) ;
    public final EObject ruleSimpleStatement() throws RecognitionException {
        EObject current = null;

        EObject this_StoreStatement_0 = null;

        EObject this_PrintStatement_1 = null;

        EObject this_GotoStatement_2 = null;



        	enterRule();

        try {
            // InternalL101.g:1465:2: ( (this_StoreStatement_0= ruleStoreStatement | this_PrintStatement_1= rulePrintStatement | this_GotoStatement_2= ruleGotoStatement ) )
            // InternalL101.g:1466:2: (this_StoreStatement_0= ruleStoreStatement | this_PrintStatement_1= rulePrintStatement | this_GotoStatement_2= ruleGotoStatement )
            {
            // InternalL101.g:1466:2: (this_StoreStatement_0= ruleStoreStatement | this_PrintStatement_1= rulePrintStatement | this_GotoStatement_2= ruleGotoStatement )
            int alt23=3;
            switch ( input.LA(1) ) {
            case RULE_NUMBER:
            case 32:
            case 46:
                {
                alt23=1;
                }
                break;
            case 54:
                {
                alt23=2;
                }
                break;
            case 56:
                {
                alt23=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // InternalL101.g:1467:3: this_StoreStatement_0= ruleStoreStatement
                    {

                    			newCompositeNode(grammarAccess.getSimpleStatementAccess().getStoreStatementParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_StoreStatement_0=ruleStoreStatement();

                    state._fsp--;


                    			current = this_StoreStatement_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalL101.g:1476:3: this_PrintStatement_1= rulePrintStatement
                    {

                    			newCompositeNode(grammarAccess.getSimpleStatementAccess().getPrintStatementParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_PrintStatement_1=rulePrintStatement();

                    state._fsp--;


                    			current = this_PrintStatement_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalL101.g:1485:3: this_GotoStatement_2= ruleGotoStatement
                    {

                    			newCompositeNode(grammarAccess.getSimpleStatementAccess().getGotoStatementParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_GotoStatement_2=ruleGotoStatement();

                    state._fsp--;


                    			current = this_GotoStatement_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleStatement"


    // $ANTLR start "entryRuleSimpleControlStatement"
    // InternalL101.g:1497:1: entryRuleSimpleControlStatement returns [EObject current=null] : iv_ruleSimpleControlStatement= ruleSimpleControlStatement EOF ;
    public final EObject entryRuleSimpleControlStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleControlStatement = null;


        try {
            // InternalL101.g:1497:63: (iv_ruleSimpleControlStatement= ruleSimpleControlStatement EOF )
            // InternalL101.g:1498:2: iv_ruleSimpleControlStatement= ruleSimpleControlStatement EOF
            {
             newCompositeNode(grammarAccess.getSimpleControlStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleControlStatement=ruleSimpleControlStatement();

            state._fsp--;

             current =iv_ruleSimpleControlStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleControlStatement"


    // $ANTLR start "ruleSimpleControlStatement"
    // InternalL101.g:1504:1: ruleSimpleControlStatement returns [EObject current=null] : (this_ContinueStatement_0= ruleContinueStatement | this_BreakStatement_1= ruleBreakStatement ) ;
    public final EObject ruleSimpleControlStatement() throws RecognitionException {
        EObject current = null;

        EObject this_ContinueStatement_0 = null;

        EObject this_BreakStatement_1 = null;



        	enterRule();

        try {
            // InternalL101.g:1510:2: ( (this_ContinueStatement_0= ruleContinueStatement | this_BreakStatement_1= ruleBreakStatement ) )
            // InternalL101.g:1511:2: (this_ContinueStatement_0= ruleContinueStatement | this_BreakStatement_1= ruleBreakStatement )
            {
            // InternalL101.g:1511:2: (this_ContinueStatement_0= ruleContinueStatement | this_BreakStatement_1= ruleBreakStatement )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==57) ) {
                alt24=1;
            }
            else if ( (LA24_0==58) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // InternalL101.g:1512:3: this_ContinueStatement_0= ruleContinueStatement
                    {

                    			newCompositeNode(grammarAccess.getSimpleControlStatementAccess().getContinueStatementParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_ContinueStatement_0=ruleContinueStatement();

                    state._fsp--;


                    			current = this_ContinueStatement_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalL101.g:1521:3: this_BreakStatement_1= ruleBreakStatement
                    {

                    			newCompositeNode(grammarAccess.getSimpleControlStatementAccess().getBreakStatementParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_BreakStatement_1=ruleBreakStatement();

                    state._fsp--;


                    			current = this_BreakStatement_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleControlStatement"


    // $ANTLR start "entryRuleStructuredControlStatement"
    // InternalL101.g:1533:1: entryRuleStructuredControlStatement returns [EObject current=null] : iv_ruleStructuredControlStatement= ruleStructuredControlStatement EOF ;
    public final EObject entryRuleStructuredControlStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStructuredControlStatement = null;


        try {
            // InternalL101.g:1533:67: (iv_ruleStructuredControlStatement= ruleStructuredControlStatement EOF )
            // InternalL101.g:1534:2: iv_ruleStructuredControlStatement= ruleStructuredControlStatement EOF
            {
             newCompositeNode(grammarAccess.getStructuredControlStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStructuredControlStatement=ruleStructuredControlStatement();

            state._fsp--;

             current =iv_ruleStructuredControlStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStructuredControlStatement"


    // $ANTLR start "ruleStructuredControlStatement"
    // InternalL101.g:1540:1: ruleStructuredControlStatement returns [EObject current=null] : (this_IfStatement_0= ruleIfStatement | this_LoopStatement_1= ruleLoopStatement ) ;
    public final EObject ruleStructuredControlStatement() throws RecognitionException {
        EObject current = null;

        EObject this_IfStatement_0 = null;

        EObject this_LoopStatement_1 = null;



        	enterRule();

        try {
            // InternalL101.g:1546:2: ( (this_IfStatement_0= ruleIfStatement | this_LoopStatement_1= ruleLoopStatement ) )
            // InternalL101.g:1547:2: (this_IfStatement_0= ruleIfStatement | this_LoopStatement_1= ruleLoopStatement )
            {
            // InternalL101.g:1547:2: (this_IfStatement_0= ruleIfStatement | this_LoopStatement_1= ruleLoopStatement )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==59) ) {
                alt25=1;
            }
            else if ( ((LA25_0>=63 && LA25_0<=65)) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // InternalL101.g:1548:3: this_IfStatement_0= ruleIfStatement
                    {

                    			newCompositeNode(grammarAccess.getStructuredControlStatementAccess().getIfStatementParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IfStatement_0=ruleIfStatement();

                    state._fsp--;


                    			current = this_IfStatement_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalL101.g:1557:3: this_LoopStatement_1= ruleLoopStatement
                    {

                    			newCompositeNode(grammarAccess.getStructuredControlStatementAccess().getLoopStatementParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_LoopStatement_1=ruleLoopStatement();

                    state._fsp--;


                    			current = this_LoopStatement_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStructuredControlStatement"


    // $ANTLR start "entryRuleLoopStatement"
    // InternalL101.g:1569:1: entryRuleLoopStatement returns [EObject current=null] : iv_ruleLoopStatement= ruleLoopStatement EOF ;
    public final EObject entryRuleLoopStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoopStatement = null;


        try {
            // InternalL101.g:1569:54: (iv_ruleLoopStatement= ruleLoopStatement EOF )
            // InternalL101.g:1570:2: iv_ruleLoopStatement= ruleLoopStatement EOF
            {
             newCompositeNode(grammarAccess.getLoopStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLoopStatement=ruleLoopStatement();

            state._fsp--;

             current =iv_ruleLoopStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLoopStatement"


    // $ANTLR start "ruleLoopStatement"
    // InternalL101.g:1576:1: ruleLoopStatement returns [EObject current=null] : (this_UntilStatement_0= ruleUntilStatement | this_ForStatement_1= ruleForStatement | this_WhileStatement_2= ruleWhileStatement ) ;
    public final EObject ruleLoopStatement() throws RecognitionException {
        EObject current = null;

        EObject this_UntilStatement_0 = null;

        EObject this_ForStatement_1 = null;

        EObject this_WhileStatement_2 = null;



        	enterRule();

        try {
            // InternalL101.g:1582:2: ( (this_UntilStatement_0= ruleUntilStatement | this_ForStatement_1= ruleForStatement | this_WhileStatement_2= ruleWhileStatement ) )
            // InternalL101.g:1583:2: (this_UntilStatement_0= ruleUntilStatement | this_ForStatement_1= ruleForStatement | this_WhileStatement_2= ruleWhileStatement )
            {
            // InternalL101.g:1583:2: (this_UntilStatement_0= ruleUntilStatement | this_ForStatement_1= ruleForStatement | this_WhileStatement_2= ruleWhileStatement )
            int alt26=3;
            switch ( input.LA(1) ) {
            case 63:
                {
                alt26=1;
                }
                break;
            case 65:
                {
                alt26=2;
                }
                break;
            case 64:
                {
                alt26=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // InternalL101.g:1584:3: this_UntilStatement_0= ruleUntilStatement
                    {

                    			newCompositeNode(grammarAccess.getLoopStatementAccess().getUntilStatementParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_UntilStatement_0=ruleUntilStatement();

                    state._fsp--;


                    			current = this_UntilStatement_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalL101.g:1593:3: this_ForStatement_1= ruleForStatement
                    {

                    			newCompositeNode(grammarAccess.getLoopStatementAccess().getForStatementParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ForStatement_1=ruleForStatement();

                    state._fsp--;


                    			current = this_ForStatement_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalL101.g:1602:3: this_WhileStatement_2= ruleWhileStatement
                    {

                    			newCompositeNode(grammarAccess.getLoopStatementAccess().getWhileStatementParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_WhileStatement_2=ruleWhileStatement();

                    state._fsp--;


                    			current = this_WhileStatement_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLoopStatement"


    // $ANTLR start "entryRuleStoreStatement"
    // InternalL101.g:1614:1: entryRuleStoreStatement returns [EObject current=null] : iv_ruleStoreStatement= ruleStoreStatement EOF ;
    public final EObject entryRuleStoreStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStoreStatement = null;


        try {
            // InternalL101.g:1614:55: (iv_ruleStoreStatement= ruleStoreStatement EOF )
            // InternalL101.g:1615:2: iv_ruleStoreStatement= ruleStoreStatement EOF
            {
             newCompositeNode(grammarAccess.getStoreStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStoreStatement=ruleStoreStatement();

            state._fsp--;

             current =iv_ruleStoreStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStoreStatement"


    // $ANTLR start "ruleStoreStatement"
    // InternalL101.g:1621:1: ruleStoreStatement returns [EObject current=null] : ( ( ( (lv_param_0_1= ruleConstant | lv_param_0_2= ruleUserInput ) ) ) otherlv_1= '->' ( (lv_store_2_0= ruleVariableAccess ) ) ) ;
    public final EObject ruleStoreStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_param_0_1 = null;

        EObject lv_param_0_2 = null;

        EObject lv_store_2_0 = null;



        	enterRule();

        try {
            // InternalL101.g:1627:2: ( ( ( ( (lv_param_0_1= ruleConstant | lv_param_0_2= ruleUserInput ) ) ) otherlv_1= '->' ( (lv_store_2_0= ruleVariableAccess ) ) ) )
            // InternalL101.g:1628:2: ( ( ( (lv_param_0_1= ruleConstant | lv_param_0_2= ruleUserInput ) ) ) otherlv_1= '->' ( (lv_store_2_0= ruleVariableAccess ) ) )
            {
            // InternalL101.g:1628:2: ( ( ( (lv_param_0_1= ruleConstant | lv_param_0_2= ruleUserInput ) ) ) otherlv_1= '->' ( (lv_store_2_0= ruleVariableAccess ) ) )
            // InternalL101.g:1629:3: ( ( (lv_param_0_1= ruleConstant | lv_param_0_2= ruleUserInput ) ) ) otherlv_1= '->' ( (lv_store_2_0= ruleVariableAccess ) )
            {
            // InternalL101.g:1629:3: ( ( (lv_param_0_1= ruleConstant | lv_param_0_2= ruleUserInput ) ) )
            // InternalL101.g:1630:4: ( (lv_param_0_1= ruleConstant | lv_param_0_2= ruleUserInput ) )
            {
            // InternalL101.g:1630:4: ( (lv_param_0_1= ruleConstant | lv_param_0_2= ruleUserInput ) )
            // InternalL101.g:1631:5: (lv_param_0_1= ruleConstant | lv_param_0_2= ruleUserInput )
            {
            // InternalL101.g:1631:5: (lv_param_0_1= ruleConstant | lv_param_0_2= ruleUserInput )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==RULE_NUMBER||LA27_0==32) ) {
                alt27=1;
            }
            else if ( (LA27_0==46) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // InternalL101.g:1632:6: lv_param_0_1= ruleConstant
                    {

                    						newCompositeNode(grammarAccess.getStoreStatementAccess().getParamConstantParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_21);
                    lv_param_0_1=ruleConstant();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getStoreStatementRule());
                    						}
                    						set(
                    							current,
                    							"param",
                    							lv_param_0_1,
                    							"org.mzattera.dsl.L101.Constant");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 2 :
                    // InternalL101.g:1648:6: lv_param_0_2= ruleUserInput
                    {

                    						newCompositeNode(grammarAccess.getStoreStatementAccess().getParamUserInputParserRuleCall_0_0_1());
                    					
                    pushFollow(FOLLOW_21);
                    lv_param_0_2=ruleUserInput();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getStoreStatementRule());
                    						}
                    						set(
                    							current,
                    							"param",
                    							lv_param_0_2,
                    							"org.mzattera.dsl.L101.UserInput");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;

            }


            }


            }

            otherlv_1=(Token)match(input,53,FOLLOW_22); 

            			newLeafNode(otherlv_1, grammarAccess.getStoreStatementAccess().getHyphenMinusGreaterThanSignKeyword_1());
            		
            // InternalL101.g:1670:3: ( (lv_store_2_0= ruleVariableAccess ) )
            // InternalL101.g:1671:4: (lv_store_2_0= ruleVariableAccess )
            {
            // InternalL101.g:1671:4: (lv_store_2_0= ruleVariableAccess )
            // InternalL101.g:1672:5: lv_store_2_0= ruleVariableAccess
            {

            					newCompositeNode(grammarAccess.getStoreStatementAccess().getStoreVariableAccessParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_store_2_0=ruleVariableAccess();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStoreStatementRule());
            					}
            					set(
            						current,
            						"store",
            						lv_store_2_0,
            						"org.mzattera.dsl.L101.VariableAccess");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStoreStatement"


    // $ANTLR start "entryRulePrintStatement"
    // InternalL101.g:1693:1: entryRulePrintStatement returns [EObject current=null] : iv_rulePrintStatement= rulePrintStatement EOF ;
    public final EObject entryRulePrintStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrintStatement = null;


        try {
            // InternalL101.g:1693:55: (iv_rulePrintStatement= rulePrintStatement EOF )
            // InternalL101.g:1694:2: iv_rulePrintStatement= rulePrintStatement EOF
            {
             newCompositeNode(grammarAccess.getPrintStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrintStatement=rulePrintStatement();

            state._fsp--;

             current =iv_rulePrintStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrintStatement"


    // $ANTLR start "rulePrintStatement"
    // InternalL101.g:1700:1: rulePrintStatement returns [EObject current=null] : ( () otherlv_1= 'PRINT' ( ( (lv_params_2_0= rulePrintParameter ) ) (otherlv_3= ',' ( (lv_params_4_0= rulePrintParameter ) ) )* )? ) ;
    public final EObject rulePrintStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_params_2_0 = null;

        EObject lv_params_4_0 = null;



        	enterRule();

        try {
            // InternalL101.g:1706:2: ( ( () otherlv_1= 'PRINT' ( ( (lv_params_2_0= rulePrintParameter ) ) (otherlv_3= ',' ( (lv_params_4_0= rulePrintParameter ) ) )* )? ) )
            // InternalL101.g:1707:2: ( () otherlv_1= 'PRINT' ( ( (lv_params_2_0= rulePrintParameter ) ) (otherlv_3= ',' ( (lv_params_4_0= rulePrintParameter ) ) )* )? )
            {
            // InternalL101.g:1707:2: ( () otherlv_1= 'PRINT' ( ( (lv_params_2_0= rulePrintParameter ) ) (otherlv_3= ',' ( (lv_params_4_0= rulePrintParameter ) ) )* )? )
            // InternalL101.g:1708:3: () otherlv_1= 'PRINT' ( ( (lv_params_2_0= rulePrintParameter ) ) (otherlv_3= ',' ( (lv_params_4_0= rulePrintParameter ) ) )* )?
            {
            // InternalL101.g:1708:3: ()
            // InternalL101.g:1709:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getPrintStatementAccess().getPrintStatementAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,54,FOLLOW_23); 

            			newLeafNode(otherlv_1, grammarAccess.getPrintStatementAccess().getPRINTKeyword_1());
            		
            // InternalL101.g:1719:3: ( ( (lv_params_2_0= rulePrintParameter ) ) (otherlv_3= ',' ( (lv_params_4_0= rulePrintParameter ) ) )* )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=RULE_ID && LA29_0<=RULE_NUMBER)||LA29_0==32||LA29_0==45||LA29_0==55) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalL101.g:1720:4: ( (lv_params_2_0= rulePrintParameter ) ) (otherlv_3= ',' ( (lv_params_4_0= rulePrintParameter ) ) )*
                    {
                    // InternalL101.g:1720:4: ( (lv_params_2_0= rulePrintParameter ) )
                    // InternalL101.g:1721:5: (lv_params_2_0= rulePrintParameter )
                    {
                    // InternalL101.g:1721:5: (lv_params_2_0= rulePrintParameter )
                    // InternalL101.g:1722:6: lv_params_2_0= rulePrintParameter
                    {

                    						newCompositeNode(grammarAccess.getPrintStatementAccess().getParamsPrintParameterParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_24);
                    lv_params_2_0=rulePrintParameter();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPrintStatementRule());
                    						}
                    						add(
                    							current,
                    							"params",
                    							lv_params_2_0,
                    							"org.mzattera.dsl.L101.PrintParameter");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalL101.g:1739:4: (otherlv_3= ',' ( (lv_params_4_0= rulePrintParameter ) ) )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==11) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // InternalL101.g:1740:5: otherlv_3= ',' ( (lv_params_4_0= rulePrintParameter ) )
                    	    {
                    	    otherlv_3=(Token)match(input,11,FOLLOW_25); 

                    	    					newLeafNode(otherlv_3, grammarAccess.getPrintStatementAccess().getCommaKeyword_2_1_0());
                    	    				
                    	    // InternalL101.g:1744:5: ( (lv_params_4_0= rulePrintParameter ) )
                    	    // InternalL101.g:1745:6: (lv_params_4_0= rulePrintParameter )
                    	    {
                    	    // InternalL101.g:1745:6: (lv_params_4_0= rulePrintParameter )
                    	    // InternalL101.g:1746:7: lv_params_4_0= rulePrintParameter
                    	    {

                    	    							newCompositeNode(grammarAccess.getPrintStatementAccess().getParamsPrintParameterParserRuleCall_2_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_24);
                    	    lv_params_4_0=rulePrintParameter();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getPrintStatementRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"params",
                    	    								lv_params_4_0,
                    	    								"org.mzattera.dsl.L101.PrintParameter");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrintStatement"


    // $ANTLR start "entryRulePrintParameter"
    // InternalL101.g:1769:1: entryRulePrintParameter returns [EObject current=null] : iv_rulePrintParameter= rulePrintParameter EOF ;
    public final EObject entryRulePrintParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrintParameter = null;


        try {
            // InternalL101.g:1769:55: (iv_rulePrintParameter= rulePrintParameter EOF )
            // InternalL101.g:1770:2: iv_rulePrintParameter= rulePrintParameter EOF
            {
             newCompositeNode(grammarAccess.getPrintParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrintParameter=rulePrintParameter();

            state._fsp--;

             current =iv_rulePrintParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrintParameter"


    // $ANTLR start "rulePrintParameter"
    // InternalL101.g:1776:1: rulePrintParameter returns [EObject current=null] : (this_VariableAccess_0= ruleVariableAccess | this_Current_1= ruleCurrent | this_Constant_2= ruleConstant | this_NewLine_3= ruleNewLine ) ;
    public final EObject rulePrintParameter() throws RecognitionException {
        EObject current = null;

        EObject this_VariableAccess_0 = null;

        EObject this_Current_1 = null;

        EObject this_Constant_2 = null;

        EObject this_NewLine_3 = null;



        	enterRule();

        try {
            // InternalL101.g:1782:2: ( (this_VariableAccess_0= ruleVariableAccess | this_Current_1= ruleCurrent | this_Constant_2= ruleConstant | this_NewLine_3= ruleNewLine ) )
            // InternalL101.g:1783:2: (this_VariableAccess_0= ruleVariableAccess | this_Current_1= ruleCurrent | this_Constant_2= ruleConstant | this_NewLine_3= ruleNewLine )
            {
            // InternalL101.g:1783:2: (this_VariableAccess_0= ruleVariableAccess | this_Current_1= ruleCurrent | this_Constant_2= ruleConstant | this_NewLine_3= ruleNewLine )
            int alt30=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt30=1;
                }
                break;
            case 45:
                {
                alt30=2;
                }
                break;
            case RULE_NUMBER:
            case 32:
                {
                alt30=3;
                }
                break;
            case 55:
                {
                alt30=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // InternalL101.g:1784:3: this_VariableAccess_0= ruleVariableAccess
                    {

                    			newCompositeNode(grammarAccess.getPrintParameterAccess().getVariableAccessParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_VariableAccess_0=ruleVariableAccess();

                    state._fsp--;


                    			current = this_VariableAccess_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalL101.g:1793:3: this_Current_1= ruleCurrent
                    {

                    			newCompositeNode(grammarAccess.getPrintParameterAccess().getCurrentParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Current_1=ruleCurrent();

                    state._fsp--;


                    			current = this_Current_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalL101.g:1802:3: this_Constant_2= ruleConstant
                    {

                    			newCompositeNode(grammarAccess.getPrintParameterAccess().getConstantParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Constant_2=ruleConstant();

                    state._fsp--;


                    			current = this_Constant_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalL101.g:1811:3: this_NewLine_3= ruleNewLine
                    {

                    			newCompositeNode(grammarAccess.getPrintParameterAccess().getNewLineParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_NewLine_3=ruleNewLine();

                    state._fsp--;


                    			current = this_NewLine_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrintParameter"


    // $ANTLR start "entryRuleNewLine"
    // InternalL101.g:1823:1: entryRuleNewLine returns [EObject current=null] : iv_ruleNewLine= ruleNewLine EOF ;
    public final EObject entryRuleNewLine() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNewLine = null;


        try {
            // InternalL101.g:1823:48: (iv_ruleNewLine= ruleNewLine EOF )
            // InternalL101.g:1824:2: iv_ruleNewLine= ruleNewLine EOF
            {
             newCompositeNode(grammarAccess.getNewLineRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNewLine=ruleNewLine();

            state._fsp--;

             current =iv_ruleNewLine; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNewLine"


    // $ANTLR start "ruleNewLine"
    // InternalL101.g:1830:1: ruleNewLine returns [EObject current=null] : ( () otherlv_1= 'NL' ) ;
    public final EObject ruleNewLine() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalL101.g:1836:2: ( ( () otherlv_1= 'NL' ) )
            // InternalL101.g:1837:2: ( () otherlv_1= 'NL' )
            {
            // InternalL101.g:1837:2: ( () otherlv_1= 'NL' )
            // InternalL101.g:1838:3: () otherlv_1= 'NL'
            {
            // InternalL101.g:1838:3: ()
            // InternalL101.g:1839:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getNewLineAccess().getNewLineAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,55,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getNewLineAccess().getNLKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNewLine"


    // $ANTLR start "entryRuleGotoStatement"
    // InternalL101.g:1853:1: entryRuleGotoStatement returns [EObject current=null] : iv_ruleGotoStatement= ruleGotoStatement EOF ;
    public final EObject entryRuleGotoStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGotoStatement = null;


        try {
            // InternalL101.g:1853:54: (iv_ruleGotoStatement= ruleGotoStatement EOF )
            // InternalL101.g:1854:2: iv_ruleGotoStatement= ruleGotoStatement EOF
            {
             newCompositeNode(grammarAccess.getGotoStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGotoStatement=ruleGotoStatement();

            state._fsp--;

             current =iv_ruleGotoStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGotoStatement"


    // $ANTLR start "ruleGotoStatement"
    // InternalL101.g:1860:1: ruleGotoStatement returns [EObject current=null] : (otherlv_0= 'GOTO' ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleGotoStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalL101.g:1866:2: ( (otherlv_0= 'GOTO' ( (otherlv_1= RULE_ID ) ) ) )
            // InternalL101.g:1867:2: (otherlv_0= 'GOTO' ( (otherlv_1= RULE_ID ) ) )
            {
            // InternalL101.g:1867:2: (otherlv_0= 'GOTO' ( (otherlv_1= RULE_ID ) ) )
            // InternalL101.g:1868:3: otherlv_0= 'GOTO' ( (otherlv_1= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,56,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getGotoStatementAccess().getGOTOKeyword_0());
            		
            // InternalL101.g:1872:3: ( (otherlv_1= RULE_ID ) )
            // InternalL101.g:1873:4: (otherlv_1= RULE_ID )
            {
            // InternalL101.g:1873:4: (otherlv_1= RULE_ID )
            // InternalL101.g:1874:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGotoStatementRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_1, grammarAccess.getGotoStatementAccess().getLabelLabelCrossReference_1_0());
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGotoStatement"


    // $ANTLR start "entryRuleContinueStatement"
    // InternalL101.g:1889:1: entryRuleContinueStatement returns [EObject current=null] : iv_ruleContinueStatement= ruleContinueStatement EOF ;
    public final EObject entryRuleContinueStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContinueStatement = null;


        try {
            // InternalL101.g:1889:58: (iv_ruleContinueStatement= ruleContinueStatement EOF )
            // InternalL101.g:1890:2: iv_ruleContinueStatement= ruleContinueStatement EOF
            {
             newCompositeNode(grammarAccess.getContinueStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleContinueStatement=ruleContinueStatement();

            state._fsp--;

             current =iv_ruleContinueStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleContinueStatement"


    // $ANTLR start "ruleContinueStatement"
    // InternalL101.g:1896:1: ruleContinueStatement returns [EObject current=null] : ( () ( (lv_code_1_0= 'CONTINUE' ) ) ) ;
    public final EObject ruleContinueStatement() throws RecognitionException {
        EObject current = null;

        Token lv_code_1_0=null;


        	enterRule();

        try {
            // InternalL101.g:1902:2: ( ( () ( (lv_code_1_0= 'CONTINUE' ) ) ) )
            // InternalL101.g:1903:2: ( () ( (lv_code_1_0= 'CONTINUE' ) ) )
            {
            // InternalL101.g:1903:2: ( () ( (lv_code_1_0= 'CONTINUE' ) ) )
            // InternalL101.g:1904:3: () ( (lv_code_1_0= 'CONTINUE' ) )
            {
            // InternalL101.g:1904:3: ()
            // InternalL101.g:1905:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getContinueStatementAccess().getContinueStatementAction_0(),
            					current);
            			

            }

            // InternalL101.g:1911:3: ( (lv_code_1_0= 'CONTINUE' ) )
            // InternalL101.g:1912:4: (lv_code_1_0= 'CONTINUE' )
            {
            // InternalL101.g:1912:4: (lv_code_1_0= 'CONTINUE' )
            // InternalL101.g:1913:5: lv_code_1_0= 'CONTINUE'
            {
            lv_code_1_0=(Token)match(input,57,FOLLOW_2); 

            					newLeafNode(lv_code_1_0, grammarAccess.getContinueStatementAccess().getCodeCONTINUEKeyword_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getContinueStatementRule());
            					}
            					setWithLastConsumed(current, "code", lv_code_1_0, "CONTINUE");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleContinueStatement"


    // $ANTLR start "entryRuleBreakStatement"
    // InternalL101.g:1929:1: entryRuleBreakStatement returns [EObject current=null] : iv_ruleBreakStatement= ruleBreakStatement EOF ;
    public final EObject entryRuleBreakStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBreakStatement = null;


        try {
            // InternalL101.g:1929:55: (iv_ruleBreakStatement= ruleBreakStatement EOF )
            // InternalL101.g:1930:2: iv_ruleBreakStatement= ruleBreakStatement EOF
            {
             newCompositeNode(grammarAccess.getBreakStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBreakStatement=ruleBreakStatement();

            state._fsp--;

             current =iv_ruleBreakStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBreakStatement"


    // $ANTLR start "ruleBreakStatement"
    // InternalL101.g:1936:1: ruleBreakStatement returns [EObject current=null] : ( () ( (lv_code_1_0= 'BREAK' ) ) ) ;
    public final EObject ruleBreakStatement() throws RecognitionException {
        EObject current = null;

        Token lv_code_1_0=null;


        	enterRule();

        try {
            // InternalL101.g:1942:2: ( ( () ( (lv_code_1_0= 'BREAK' ) ) ) )
            // InternalL101.g:1943:2: ( () ( (lv_code_1_0= 'BREAK' ) ) )
            {
            // InternalL101.g:1943:2: ( () ( (lv_code_1_0= 'BREAK' ) ) )
            // InternalL101.g:1944:3: () ( (lv_code_1_0= 'BREAK' ) )
            {
            // InternalL101.g:1944:3: ()
            // InternalL101.g:1945:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getBreakStatementAccess().getBreakStatementAction_0(),
            					current);
            			

            }

            // InternalL101.g:1951:3: ( (lv_code_1_0= 'BREAK' ) )
            // InternalL101.g:1952:4: (lv_code_1_0= 'BREAK' )
            {
            // InternalL101.g:1952:4: (lv_code_1_0= 'BREAK' )
            // InternalL101.g:1953:5: lv_code_1_0= 'BREAK'
            {
            lv_code_1_0=(Token)match(input,58,FOLLOW_2); 

            					newLeafNode(lv_code_1_0, grammarAccess.getBreakStatementAccess().getCodeBREAKKeyword_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getBreakStatementRule());
            					}
            					setWithLastConsumed(current, "code", lv_code_1_0, "BREAK");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBreakStatement"


    // $ANTLR start "entryRuleIfStatement"
    // InternalL101.g:1969:1: entryRuleIfStatement returns [EObject current=null] : iv_ruleIfStatement= ruleIfStatement EOF ;
    public final EObject entryRuleIfStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfStatement = null;


        try {
            // InternalL101.g:1969:52: (iv_ruleIfStatement= ruleIfStatement EOF )
            // InternalL101.g:1970:2: iv_ruleIfStatement= ruleIfStatement EOF
            {
             newCompositeNode(grammarAccess.getIfStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIfStatement=ruleIfStatement();

            state._fsp--;

             current =iv_ruleIfStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIfStatement"


    // $ANTLR start "ruleIfStatement"
    // InternalL101.g:1976:1: ruleIfStatement returns [EObject current=null] : ( ( (lv_code_0_0= 'IF' ) ) ( (lv_condition_1_0= ruleAssignmentStatement ) ) (otherlv_2= 'THEN' ( (lv_thenClause_3_0= ruleStatements ) ) )? (otherlv_4= 'ELSE' ( (lv_elseClause_5_0= ruleStatements ) ) )? otherlv_6= 'END' ) ;
    public final EObject ruleIfStatement() throws RecognitionException {
        EObject current = null;

        Token lv_code_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_condition_1_0 = null;

        EObject lv_thenClause_3_0 = null;

        EObject lv_elseClause_5_0 = null;



        	enterRule();

        try {
            // InternalL101.g:1982:2: ( ( ( (lv_code_0_0= 'IF' ) ) ( (lv_condition_1_0= ruleAssignmentStatement ) ) (otherlv_2= 'THEN' ( (lv_thenClause_3_0= ruleStatements ) ) )? (otherlv_4= 'ELSE' ( (lv_elseClause_5_0= ruleStatements ) ) )? otherlv_6= 'END' ) )
            // InternalL101.g:1983:2: ( ( (lv_code_0_0= 'IF' ) ) ( (lv_condition_1_0= ruleAssignmentStatement ) ) (otherlv_2= 'THEN' ( (lv_thenClause_3_0= ruleStatements ) ) )? (otherlv_4= 'ELSE' ( (lv_elseClause_5_0= ruleStatements ) ) )? otherlv_6= 'END' )
            {
            // InternalL101.g:1983:2: ( ( (lv_code_0_0= 'IF' ) ) ( (lv_condition_1_0= ruleAssignmentStatement ) ) (otherlv_2= 'THEN' ( (lv_thenClause_3_0= ruleStatements ) ) )? (otherlv_4= 'ELSE' ( (lv_elseClause_5_0= ruleStatements ) ) )? otherlv_6= 'END' )
            // InternalL101.g:1984:3: ( (lv_code_0_0= 'IF' ) ) ( (lv_condition_1_0= ruleAssignmentStatement ) ) (otherlv_2= 'THEN' ( (lv_thenClause_3_0= ruleStatements ) ) )? (otherlv_4= 'ELSE' ( (lv_elseClause_5_0= ruleStatements ) ) )? otherlv_6= 'END'
            {
            // InternalL101.g:1984:3: ( (lv_code_0_0= 'IF' ) )
            // InternalL101.g:1985:4: (lv_code_0_0= 'IF' )
            {
            // InternalL101.g:1985:4: (lv_code_0_0= 'IF' )
            // InternalL101.g:1986:5: lv_code_0_0= 'IF'
            {
            lv_code_0_0=(Token)match(input,59,FOLLOW_22); 

            					newLeafNode(lv_code_0_0, grammarAccess.getIfStatementAccess().getCodeIFKeyword_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIfStatementRule());
            					}
            					setWithLastConsumed(current, "code", lv_code_0_0, "IF");
            				

            }


            }

            // InternalL101.g:1998:3: ( (lv_condition_1_0= ruleAssignmentStatement ) )
            // InternalL101.g:1999:4: (lv_condition_1_0= ruleAssignmentStatement )
            {
            // InternalL101.g:1999:4: (lv_condition_1_0= ruleAssignmentStatement )
            // InternalL101.g:2000:5: lv_condition_1_0= ruleAssignmentStatement
            {

            					newCompositeNode(grammarAccess.getIfStatementAccess().getConditionAssignmentStatementParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_26);
            lv_condition_1_0=ruleAssignmentStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIfStatementRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_1_0,
            						"org.mzattera.dsl.L101.AssignmentStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalL101.g:2017:3: (otherlv_2= 'THEN' ( (lv_thenClause_3_0= ruleStatements ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==60) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalL101.g:2018:4: otherlv_2= 'THEN' ( (lv_thenClause_3_0= ruleStatements ) )
                    {
                    otherlv_2=(Token)match(input,60,FOLLOW_27); 

                    				newLeafNode(otherlv_2, grammarAccess.getIfStatementAccess().getTHENKeyword_2_0());
                    			
                    // InternalL101.g:2022:4: ( (lv_thenClause_3_0= ruleStatements ) )
                    // InternalL101.g:2023:5: (lv_thenClause_3_0= ruleStatements )
                    {
                    // InternalL101.g:2023:5: (lv_thenClause_3_0= ruleStatements )
                    // InternalL101.g:2024:6: lv_thenClause_3_0= ruleStatements
                    {

                    						newCompositeNode(grammarAccess.getIfStatementAccess().getThenClauseStatementsParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_28);
                    lv_thenClause_3_0=ruleStatements();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getIfStatementRule());
                    						}
                    						set(
                    							current,
                    							"thenClause",
                    							lv_thenClause_3_0,
                    							"org.mzattera.dsl.L101.Statements");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalL101.g:2042:3: (otherlv_4= 'ELSE' ( (lv_elseClause_5_0= ruleStatements ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==61) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalL101.g:2043:4: otherlv_4= 'ELSE' ( (lv_elseClause_5_0= ruleStatements ) )
                    {
                    otherlv_4=(Token)match(input,61,FOLLOW_29); 

                    				newLeafNode(otherlv_4, grammarAccess.getIfStatementAccess().getELSEKeyword_3_0());
                    			
                    // InternalL101.g:2047:4: ( (lv_elseClause_5_0= ruleStatements ) )
                    // InternalL101.g:2048:5: (lv_elseClause_5_0= ruleStatements )
                    {
                    // InternalL101.g:2048:5: (lv_elseClause_5_0= ruleStatements )
                    // InternalL101.g:2049:6: lv_elseClause_5_0= ruleStatements
                    {

                    						newCompositeNode(grammarAccess.getIfStatementAccess().getElseClauseStatementsParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_30);
                    lv_elseClause_5_0=ruleStatements();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getIfStatementRule());
                    						}
                    						set(
                    							current,
                    							"elseClause",
                    							lv_elseClause_5_0,
                    							"org.mzattera.dsl.L101.Statements");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,62,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getIfStatementAccess().getENDKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIfStatement"


    // $ANTLR start "entryRuleUntilStatement"
    // InternalL101.g:2075:1: entryRuleUntilStatement returns [EObject current=null] : iv_ruleUntilStatement= ruleUntilStatement EOF ;
    public final EObject entryRuleUntilStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUntilStatement = null;


        try {
            // InternalL101.g:2075:55: (iv_ruleUntilStatement= ruleUntilStatement EOF )
            // InternalL101.g:2076:2: iv_ruleUntilStatement= ruleUntilStatement EOF
            {
             newCompositeNode(grammarAccess.getUntilStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUntilStatement=ruleUntilStatement();

            state._fsp--;

             current =iv_ruleUntilStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUntilStatement"


    // $ANTLR start "ruleUntilStatement"
    // InternalL101.g:2082:1: ruleUntilStatement returns [EObject current=null] : (otherlv_0= 'UNTIL' ( (lv_condition_1_0= ruleAssignmentStatement ) ) otherlv_2= 'DO' ( (lv_body_3_0= ruleStatements ) ) otherlv_4= 'END' ) ;
    public final EObject ruleUntilStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_condition_1_0 = null;

        EObject lv_body_3_0 = null;



        	enterRule();

        try {
            // InternalL101.g:2088:2: ( (otherlv_0= 'UNTIL' ( (lv_condition_1_0= ruleAssignmentStatement ) ) otherlv_2= 'DO' ( (lv_body_3_0= ruleStatements ) ) otherlv_4= 'END' ) )
            // InternalL101.g:2089:2: (otherlv_0= 'UNTIL' ( (lv_condition_1_0= ruleAssignmentStatement ) ) otherlv_2= 'DO' ( (lv_body_3_0= ruleStatements ) ) otherlv_4= 'END' )
            {
            // InternalL101.g:2089:2: (otherlv_0= 'UNTIL' ( (lv_condition_1_0= ruleAssignmentStatement ) ) otherlv_2= 'DO' ( (lv_body_3_0= ruleStatements ) ) otherlv_4= 'END' )
            // InternalL101.g:2090:3: otherlv_0= 'UNTIL' ( (lv_condition_1_0= ruleAssignmentStatement ) ) otherlv_2= 'DO' ( (lv_body_3_0= ruleStatements ) ) otherlv_4= 'END'
            {
            otherlv_0=(Token)match(input,63,FOLLOW_22); 

            			newLeafNode(otherlv_0, grammarAccess.getUntilStatementAccess().getUNTILKeyword_0());
            		
            // InternalL101.g:2094:3: ( (lv_condition_1_0= ruleAssignmentStatement ) )
            // InternalL101.g:2095:4: (lv_condition_1_0= ruleAssignmentStatement )
            {
            // InternalL101.g:2095:4: (lv_condition_1_0= ruleAssignmentStatement )
            // InternalL101.g:2096:5: lv_condition_1_0= ruleAssignmentStatement
            {

            					newCompositeNode(grammarAccess.getUntilStatementAccess().getConditionAssignmentStatementParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_31);
            lv_condition_1_0=ruleAssignmentStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getUntilStatementRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_1_0,
            						"org.mzattera.dsl.L101.AssignmentStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,64,FOLLOW_29); 

            			newLeafNode(otherlv_2, grammarAccess.getUntilStatementAccess().getDOKeyword_2());
            		
            // InternalL101.g:2117:3: ( (lv_body_3_0= ruleStatements ) )
            // InternalL101.g:2118:4: (lv_body_3_0= ruleStatements )
            {
            // InternalL101.g:2118:4: (lv_body_3_0= ruleStatements )
            // InternalL101.g:2119:5: lv_body_3_0= ruleStatements
            {

            					newCompositeNode(grammarAccess.getUntilStatementAccess().getBodyStatementsParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_30);
            lv_body_3_0=ruleStatements();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getUntilStatementRule());
            					}
            					set(
            						current,
            						"body",
            						lv_body_3_0,
            						"org.mzattera.dsl.L101.Statements");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,62,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getUntilStatementAccess().getENDKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUntilStatement"


    // $ANTLR start "entryRuleForStatement"
    // InternalL101.g:2144:1: entryRuleForStatement returns [EObject current=null] : iv_ruleForStatement= ruleForStatement EOF ;
    public final EObject entryRuleForStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForStatement = null;


        try {
            // InternalL101.g:2144:53: (iv_ruleForStatement= ruleForStatement EOF )
            // InternalL101.g:2145:2: iv_ruleForStatement= ruleForStatement EOF
            {
             newCompositeNode(grammarAccess.getForStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleForStatement=ruleForStatement();

            state._fsp--;

             current =iv_ruleForStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForStatement"


    // $ANTLR start "ruleForStatement"
    // InternalL101.g:2151:1: ruleForStatement returns [EObject current=null] : (otherlv_0= 'FOR' ( ( (lv_init_1_1= ruleAssignmentStatement | lv_init_1_2= ruleSimpleStatement ) ) )? otherlv_2= ';' ( (lv_condition_3_0= ruleAssignmentStatement ) ) otherlv_4= ';' ( ( (lv_end_5_1= ruleAssignmentStatement | lv_end_5_2= ruleSimpleStatement ) ) )? otherlv_6= 'DO' ( (lv_body_7_0= ruleStatements ) ) otherlv_8= 'END' ) ;
    public final EObject ruleForStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_init_1_1 = null;

        EObject lv_init_1_2 = null;

        EObject lv_condition_3_0 = null;

        EObject lv_end_5_1 = null;

        EObject lv_end_5_2 = null;

        EObject lv_body_7_0 = null;



        	enterRule();

        try {
            // InternalL101.g:2157:2: ( (otherlv_0= 'FOR' ( ( (lv_init_1_1= ruleAssignmentStatement | lv_init_1_2= ruleSimpleStatement ) ) )? otherlv_2= ';' ( (lv_condition_3_0= ruleAssignmentStatement ) ) otherlv_4= ';' ( ( (lv_end_5_1= ruleAssignmentStatement | lv_end_5_2= ruleSimpleStatement ) ) )? otherlv_6= 'DO' ( (lv_body_7_0= ruleStatements ) ) otherlv_8= 'END' ) )
            // InternalL101.g:2158:2: (otherlv_0= 'FOR' ( ( (lv_init_1_1= ruleAssignmentStatement | lv_init_1_2= ruleSimpleStatement ) ) )? otherlv_2= ';' ( (lv_condition_3_0= ruleAssignmentStatement ) ) otherlv_4= ';' ( ( (lv_end_5_1= ruleAssignmentStatement | lv_end_5_2= ruleSimpleStatement ) ) )? otherlv_6= 'DO' ( (lv_body_7_0= ruleStatements ) ) otherlv_8= 'END' )
            {
            // InternalL101.g:2158:2: (otherlv_0= 'FOR' ( ( (lv_init_1_1= ruleAssignmentStatement | lv_init_1_2= ruleSimpleStatement ) ) )? otherlv_2= ';' ( (lv_condition_3_0= ruleAssignmentStatement ) ) otherlv_4= ';' ( ( (lv_end_5_1= ruleAssignmentStatement | lv_end_5_2= ruleSimpleStatement ) ) )? otherlv_6= 'DO' ( (lv_body_7_0= ruleStatements ) ) otherlv_8= 'END' )
            // InternalL101.g:2159:3: otherlv_0= 'FOR' ( ( (lv_init_1_1= ruleAssignmentStatement | lv_init_1_2= ruleSimpleStatement ) ) )? otherlv_2= ';' ( (lv_condition_3_0= ruleAssignmentStatement ) ) otherlv_4= ';' ( ( (lv_end_5_1= ruleAssignmentStatement | lv_end_5_2= ruleSimpleStatement ) ) )? otherlv_6= 'DO' ( (lv_body_7_0= ruleStatements ) ) otherlv_8= 'END'
            {
            otherlv_0=(Token)match(input,65,FOLLOW_32); 

            			newLeafNode(otherlv_0, grammarAccess.getForStatementAccess().getFORKeyword_0());
            		
            // InternalL101.g:2163:3: ( ( (lv_init_1_1= ruleAssignmentStatement | lv_init_1_2= ruleSimpleStatement ) ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( ((LA34_0>=RULE_ID && LA34_0<=RULE_NUMBER)||(LA34_0>=24 && LA34_0<=29)||LA34_0==32||LA34_0==46||LA34_0==54||LA34_0==56) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalL101.g:2164:4: ( (lv_init_1_1= ruleAssignmentStatement | lv_init_1_2= ruleSimpleStatement ) )
                    {
                    // InternalL101.g:2164:4: ( (lv_init_1_1= ruleAssignmentStatement | lv_init_1_2= ruleSimpleStatement ) )
                    // InternalL101.g:2165:5: (lv_init_1_1= ruleAssignmentStatement | lv_init_1_2= ruleSimpleStatement )
                    {
                    // InternalL101.g:2165:5: (lv_init_1_1= ruleAssignmentStatement | lv_init_1_2= ruleSimpleStatement )
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==RULE_ID||(LA33_0>=24 && LA33_0<=29)) ) {
                        alt33=1;
                    }
                    else if ( (LA33_0==RULE_NUMBER||LA33_0==32||LA33_0==46||LA33_0==54||LA33_0==56) ) {
                        alt33=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 33, 0, input);

                        throw nvae;
                    }
                    switch (alt33) {
                        case 1 :
                            // InternalL101.g:2166:6: lv_init_1_1= ruleAssignmentStatement
                            {

                            						newCompositeNode(grammarAccess.getForStatementAccess().getInitAssignmentStatementParserRuleCall_1_0_0());
                            					
                            pushFollow(FOLLOW_17);
                            lv_init_1_1=ruleAssignmentStatement();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getForStatementRule());
                            						}
                            						set(
                            							current,
                            							"init",
                            							lv_init_1_1,
                            							"org.mzattera.dsl.L101.AssignmentStatement");
                            						afterParserOrEnumRuleCall();
                            					

                            }
                            break;
                        case 2 :
                            // InternalL101.g:2182:6: lv_init_1_2= ruleSimpleStatement
                            {

                            						newCompositeNode(grammarAccess.getForStatementAccess().getInitSimpleStatementParserRuleCall_1_0_1());
                            					
                            pushFollow(FOLLOW_17);
                            lv_init_1_2=ruleSimpleStatement();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getForStatementRule());
                            						}
                            						set(
                            							current,
                            							"init",
                            							lv_init_1_2,
                            							"org.mzattera.dsl.L101.SimpleStatement");
                            						afterParserOrEnumRuleCall();
                            					

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,12,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getForStatementAccess().getSemicolonKeyword_2());
            		
            // InternalL101.g:2204:3: ( (lv_condition_3_0= ruleAssignmentStatement ) )
            // InternalL101.g:2205:4: (lv_condition_3_0= ruleAssignmentStatement )
            {
            // InternalL101.g:2205:4: (lv_condition_3_0= ruleAssignmentStatement )
            // InternalL101.g:2206:5: lv_condition_3_0= ruleAssignmentStatement
            {

            					newCompositeNode(grammarAccess.getForStatementAccess().getConditionAssignmentStatementParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_17);
            lv_condition_3_0=ruleAssignmentStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForStatementRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_3_0,
            						"org.mzattera.dsl.L101.AssignmentStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,12,FOLLOW_33); 

            			newLeafNode(otherlv_4, grammarAccess.getForStatementAccess().getSemicolonKeyword_4());
            		
            // InternalL101.g:2227:3: ( ( (lv_end_5_1= ruleAssignmentStatement | lv_end_5_2= ruleSimpleStatement ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=RULE_ID && LA36_0<=RULE_NUMBER)||(LA36_0>=24 && LA36_0<=29)||LA36_0==32||LA36_0==46||LA36_0==54||LA36_0==56) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalL101.g:2228:4: ( (lv_end_5_1= ruleAssignmentStatement | lv_end_5_2= ruleSimpleStatement ) )
                    {
                    // InternalL101.g:2228:4: ( (lv_end_5_1= ruleAssignmentStatement | lv_end_5_2= ruleSimpleStatement ) )
                    // InternalL101.g:2229:5: (lv_end_5_1= ruleAssignmentStatement | lv_end_5_2= ruleSimpleStatement )
                    {
                    // InternalL101.g:2229:5: (lv_end_5_1= ruleAssignmentStatement | lv_end_5_2= ruleSimpleStatement )
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==RULE_ID||(LA35_0>=24 && LA35_0<=29)) ) {
                        alt35=1;
                    }
                    else if ( (LA35_0==RULE_NUMBER||LA35_0==32||LA35_0==46||LA35_0==54||LA35_0==56) ) {
                        alt35=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 35, 0, input);

                        throw nvae;
                    }
                    switch (alt35) {
                        case 1 :
                            // InternalL101.g:2230:6: lv_end_5_1= ruleAssignmentStatement
                            {

                            						newCompositeNode(grammarAccess.getForStatementAccess().getEndAssignmentStatementParserRuleCall_5_0_0());
                            					
                            pushFollow(FOLLOW_31);
                            lv_end_5_1=ruleAssignmentStatement();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getForStatementRule());
                            						}
                            						set(
                            							current,
                            							"end",
                            							lv_end_5_1,
                            							"org.mzattera.dsl.L101.AssignmentStatement");
                            						afterParserOrEnumRuleCall();
                            					

                            }
                            break;
                        case 2 :
                            // InternalL101.g:2246:6: lv_end_5_2= ruleSimpleStatement
                            {

                            						newCompositeNode(grammarAccess.getForStatementAccess().getEndSimpleStatementParserRuleCall_5_0_1());
                            					
                            pushFollow(FOLLOW_31);
                            lv_end_5_2=ruleSimpleStatement();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getForStatementRule());
                            						}
                            						set(
                            							current,
                            							"end",
                            							lv_end_5_2,
                            							"org.mzattera.dsl.L101.SimpleStatement");
                            						afterParserOrEnumRuleCall();
                            					

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,64,FOLLOW_29); 

            			newLeafNode(otherlv_6, grammarAccess.getForStatementAccess().getDOKeyword_6());
            		
            // InternalL101.g:2268:3: ( (lv_body_7_0= ruleStatements ) )
            // InternalL101.g:2269:4: (lv_body_7_0= ruleStatements )
            {
            // InternalL101.g:2269:4: (lv_body_7_0= ruleStatements )
            // InternalL101.g:2270:5: lv_body_7_0= ruleStatements
            {

            					newCompositeNode(grammarAccess.getForStatementAccess().getBodyStatementsParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_30);
            lv_body_7_0=ruleStatements();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForStatementRule());
            					}
            					set(
            						current,
            						"body",
            						lv_body_7_0,
            						"org.mzattera.dsl.L101.Statements");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,62,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getForStatementAccess().getENDKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForStatement"


    // $ANTLR start "entryRuleWhileStatement"
    // InternalL101.g:2295:1: entryRuleWhileStatement returns [EObject current=null] : iv_ruleWhileStatement= ruleWhileStatement EOF ;
    public final EObject entryRuleWhileStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhileStatement = null;


        try {
            // InternalL101.g:2295:55: (iv_ruleWhileStatement= ruleWhileStatement EOF )
            // InternalL101.g:2296:2: iv_ruleWhileStatement= ruleWhileStatement EOF
            {
             newCompositeNode(grammarAccess.getWhileStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWhileStatement=ruleWhileStatement();

            state._fsp--;

             current =iv_ruleWhileStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWhileStatement"


    // $ANTLR start "ruleWhileStatement"
    // InternalL101.g:2302:1: ruleWhileStatement returns [EObject current=null] : (otherlv_0= 'DO' ( (lv_body_1_0= ruleStatements ) ) otherlv_2= 'WHILE' ( (lv_condition_3_0= ruleAssignmentStatement ) ) otherlv_4= 'END' ) ;
    public final EObject ruleWhileStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_body_1_0 = null;

        EObject lv_condition_3_0 = null;



        	enterRule();

        try {
            // InternalL101.g:2308:2: ( (otherlv_0= 'DO' ( (lv_body_1_0= ruleStatements ) ) otherlv_2= 'WHILE' ( (lv_condition_3_0= ruleAssignmentStatement ) ) otherlv_4= 'END' ) )
            // InternalL101.g:2309:2: (otherlv_0= 'DO' ( (lv_body_1_0= ruleStatements ) ) otherlv_2= 'WHILE' ( (lv_condition_3_0= ruleAssignmentStatement ) ) otherlv_4= 'END' )
            {
            // InternalL101.g:2309:2: (otherlv_0= 'DO' ( (lv_body_1_0= ruleStatements ) ) otherlv_2= 'WHILE' ( (lv_condition_3_0= ruleAssignmentStatement ) ) otherlv_4= 'END' )
            // InternalL101.g:2310:3: otherlv_0= 'DO' ( (lv_body_1_0= ruleStatements ) ) otherlv_2= 'WHILE' ( (lv_condition_3_0= ruleAssignmentStatement ) ) otherlv_4= 'END'
            {
            otherlv_0=(Token)match(input,64,FOLLOW_34); 

            			newLeafNode(otherlv_0, grammarAccess.getWhileStatementAccess().getDOKeyword_0());
            		
            // InternalL101.g:2314:3: ( (lv_body_1_0= ruleStatements ) )
            // InternalL101.g:2315:4: (lv_body_1_0= ruleStatements )
            {
            // InternalL101.g:2315:4: (lv_body_1_0= ruleStatements )
            // InternalL101.g:2316:5: lv_body_1_0= ruleStatements
            {

            					newCompositeNode(grammarAccess.getWhileStatementAccess().getBodyStatementsParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_35);
            lv_body_1_0=ruleStatements();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWhileStatementRule());
            					}
            					set(
            						current,
            						"body",
            						lv_body_1_0,
            						"org.mzattera.dsl.L101.Statements");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,66,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getWhileStatementAccess().getWHILEKeyword_2());
            		
            // InternalL101.g:2337:3: ( (lv_condition_3_0= ruleAssignmentStatement ) )
            // InternalL101.g:2338:4: (lv_condition_3_0= ruleAssignmentStatement )
            {
            // InternalL101.g:2338:4: (lv_condition_3_0= ruleAssignmentStatement )
            // InternalL101.g:2339:5: lv_condition_3_0= ruleAssignmentStatement
            {

            					newCompositeNode(grammarAccess.getWhileStatementAccess().getConditionAssignmentStatementParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_30);
            lv_condition_3_0=ruleAssignmentStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWhileStatementRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_3_0,
            						"org.mzattera.dsl.L101.AssignmentStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,62,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getWhileStatementAccess().getENDKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWhileStatement"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x8F4040013F000630L,0x0000000000000003L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000FFC000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00006FF13F000030L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x000060013F000030L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000180000002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x000040013F000030L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000E00000002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x8F4040013F000032L,0x0000000000000003L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0010800000000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x000F000000000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x000000003F000010L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x008020013F000032L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x008020013F000030L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x7000000000000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0xEF4040013F000030L,0x0000000000000003L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x6000000000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0xCF4040013F000030L,0x0000000000000003L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x014040013F001030L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x014040013F000030L,0x0000000000000001L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x8F4040013F000030L,0x0000000000000007L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});

}