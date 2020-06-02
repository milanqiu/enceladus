// Generated from D:/Career/D0_SF/Satellites/enceladus/enceladus-core/src/main/resources/antlr\DataType.g4 by ANTLR 4.6
package net.milanqiu.enceladus.antlr.generated;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DataTypeLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, WS=8, IDENTIFIER=9, 
		NONNEGATIVE_INTEGER=10;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "WS", "DIGIT", 
		"LETTER", "UNDERLINE", "IDENTIFIER", "NONNEGATIVE_INTEGER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "','", "'{'", "'}'", "'\"'", "'\":'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "WS", "IDENTIFIER", "NONNEGATIVE_INTEGER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public DataTypeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DataType.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\fJ\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\b\3\t\6\t.\n\t\r\t\16\t/\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\r\3\r\5\r<\n\r\3\r\3\r\3\r\7\rA\n\r\f\r\16\rD\13\r\3\16\6\16"+
		"G\n\16\r\16\16\16H\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\2\25\2"+
		"\27\2\31\13\33\f\3\2\6\4\2\13\13\"\"\3\2\62;\4\2C\\c|\3\2aaL\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5\37\3\2\2"+
		"\2\7!\3\2\2\2\t#\3\2\2\2\13%\3\2\2\2\r\'\3\2\2\2\17)\3\2\2\2\21-\3\2\2"+
		"\2\23\63\3\2\2\2\25\65\3\2\2\2\27\67\3\2\2\2\31;\3\2\2\2\33F\3\2\2\2\35"+
		"\36\7*\2\2\36\4\3\2\2\2\37 \7+\2\2 \6\3\2\2\2!\"\7.\2\2\"\b\3\2\2\2#$"+
		"\7}\2\2$\n\3\2\2\2%&\7\177\2\2&\f\3\2\2\2\'(\7$\2\2(\16\3\2\2\2)*\7$\2"+
		"\2*+\7<\2\2+\20\3\2\2\2,.\t\2\2\2-,\3\2\2\2./\3\2\2\2/-\3\2\2\2/\60\3"+
		"\2\2\2\60\61\3\2\2\2\61\62\b\t\2\2\62\22\3\2\2\2\63\64\t\3\2\2\64\24\3"+
		"\2\2\2\65\66\t\4\2\2\66\26\3\2\2\2\678\t\5\2\28\30\3\2\2\29<\5\25\13\2"+
		":<\5\27\f\2;9\3\2\2\2;:\3\2\2\2<B\3\2\2\2=A\5\23\n\2>A\5\25\13\2?A\5\27"+
		"\f\2@=\3\2\2\2@>\3\2\2\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\32\3"+
		"\2\2\2DB\3\2\2\2EG\5\23\n\2FE\3\2\2\2GH\3\2\2\2HF\3\2\2\2HI\3\2\2\2I\34"+
		"\3\2\2\2\b\2/;@BH\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}