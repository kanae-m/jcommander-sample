# jcommander-sample

## 1. 概要

JCommanderは、コマンドラインパラメータの解析を簡単にするフレームワークです。以下に例を示します。

com.example.chapter01.sample01

```java
public class CommandParameters {

    @Parameter(names = {"-log", "-verbose"}, description = "Level of verbosity")
    private int verbose;

    @Parameter(names = "-groups", description = "Comma-sperated list of group names to be run")
    private String groups;

    @Parameter(names = "-debug", description = "Debug mode")
    private boolean debug = false;

}
```

```java
CommandParameters parameters = new CommandParameters();
JCommander commander = JCommander.newBuilder()
        .addObject(parameters)
        .build();
commander.usage();

String[] argv = {"-log", "2", "-groups", "unit"};
commander.parse(argv);
```

```
Usage: <main class> [options]
  Options:
    -debug
      Debug mode
      Default: false
    -groups
      Comma-sperated list of group names to be run
    -log, -verbose
      Level of verbosity

[-log, 2, -groups, unit]
CommandParameters(verbose=2, groups=unit, debug=false)
```

別の例も示します。

com.example.chapter01.sample02

```java
public class CommandParameters {

    @Parameter(names = {"--length", "-l"})
    private int length;

    @Parameter(names = {"--pattern", "-p"})
    private int pattern;

}
```

```java
CommandParameters parameters = new CommandParameters();
JCommander commander = JCommander.newBuilder()
        .addObject(parameters)
        .build();
commander.usage();

String[] argv = {"-l", "512", "--pattern", "2"};
commander.parse(argv);
```

```
Usage: <main class> [options]
  Options:
    --length, -l
      Default: 0
    --pattern, -p
      Default: 0

[-l, 512, --pattern, 2]
CommandParameters(length=512, pattern=2)
```

## 2. オプションの種類

### 2.1 boolean値

このパラメータは値を指定する必要はなく、検出された場合だけ `true` となります。

com.example.chapter02.section01.sample01

```java
public class CommandParameters {

    @Parameter(names = "-debug", description = "Debug mode")
    private boolean debug = false;

}
```

```
Usage: <main class> [options]
  Options:
    -debug
      Debug mode
      Default: false

[]
CommandParameters(debug=false)

[-debug]
CommandParameters(debug=true)
```

`arity = 1` と指定した場合、ユーザーは明示的に `true` or `false` を指定する必要があります。

com.example.chapter02.section01.sample02

```java
public class CommandParameters {

    @Parameter(names = "-debug", description = "Debug mode", arity = 1)
    private boolean debug = true;

}
```

```
[]
CommandParameters(debug=false)
[-debug]
Error: Expected a value after parameter -debug
[-debug, true]
CommandParameters(debug=true)
```

### 2.2 リスト

`List` タイプのフィールドがある場合、値が複数指定される可能性があるオプションとして解釈します。

com.example.chapter02.section02

```java
public class CommandParameters {

    @Parameter(names = "-host", description = "The host")
    private List<String> hosts = new ArrayList<>();

}
```

```
Usage: <main class> [options]
  Options:
    -host
      The host
      Default: []

[-host, host1, -host, host2]
CommandParameters(hosts=[host1, host2])
```

### 2.3 パスワード

パラメータの1つを履歴に表示したくない場合は、パスワードタイプで宣言すると、コンソールに入力するように要求します。

com.example.chapter02.section03

```java
public class CommandParameters {

    @Parameter(names = "-password", description = "Connection password", password = true)
    private String password;

}
```

プログラムを実行すると、次のプロンプトが表示され、値を入力する必要があります。

```
Value for -password (Connection password):
```

### 2.4 エコー入力

デフォルトではプロンプト上で入力されたパスワードの値を確認することはできませんが、`echoInput` に `true` を設定することで確認できるようになります。

com.example.chapter02.section04

```java
public class CommandParameters {

    @Parameter(names = "-password", description = "Connection password", password = true)
    private String password;

}
```

## 3. カスタムタイプ（コンバーターとスプリッター）

### 3.1 カスタムタイプ（単一値）

`@Parameter` の `converter` 属性か `IStringConverterFactory` 実装します。

#### 3.1.1 `converter` 属性

デフォルトでは、文字列、boolean、整数のみを解析します。ファイル、ホスト名、リストなどを解析するには `IStringConverter` を継承した型コンバーターを作成する必要があります。

```java
public interface IStringConverter<T> {
    T convert(String value);
}
```

例えば、文字列をファイルに変換するコンバーターは次の通りです。

```java
public class FileConverter implements IStringConverter<File> {
    @Override
    public File convert(String value) {
      return new File(value);
    }
}
```

これを使用する方法は以下です。

com.example.chapter03.section01.item01

```java
public class CommandParameters {

    @Parameter(names = "-file", converter = FileConverter.class)
    private File file;

}
```

```
Usage: <main class> [options]
  Options:
    -file

[-file, /tmp/file]
CommandParameters(file=\tmp\file)
```

`FileConverter` はJCommanderにすでに用意されています。そのほかにも一般的なコンバーターが付属しています。

|クラス|説明|
|:--|:--|
|`BigDecimalConverter`|`BigDecimal` に変換します。|
|`BooleanConverter`|`Boolean` に変換します。|
|`CharArrayConverter`|`char[]` に変換します。|
|`DefaultListConverter<T>`|`List<T>` に変換します。|
|`DoubleConverter`|`Double` に変換します。|
|`EnumConverter<T extends Enum<T>`|列挙型 `T` に変換します。|
|`FileConverter`|`File` に変換します。|
|`FloatConverter`|`Float` に変換します。|
|`InetAddressConverter`|`InetAddress` に変換します。|
|`IntegerConverter`|`IntegerAddress` に変換します。|
|`ISO8604DateConverter`|`Date` に変換します。`yyyy-MM-dd` 形式のみ機能します。|
|`LongConverter`|`Long` に変換します。|
|`PathConverter`|`Path` に変換します。|
|`StringConverter`|`String` に変換します。|
|`URIConverter`|`URI` に変換します。|
|`URLConverter`|`URL` に変換します。|

### 3.1.2 `IStringConverterFactory`

カスタムタイプが複数回使用される場合は、`IStringConverterFactory` を使用すると便利です。

```java
public interface IStringConverterFactory {
    <T> Class<? extends IStringConverter<T>> getConverter(Class<T> forType);
}
```

例えば、`-hostport example.com:8080` というオプションをホストとポートに分けるコンバーターを実装します。

com.example.chapter03.section01.item02

ホルダークラス

```java
@AllArgsConstructor
public class HostPort {

    private final String host;
    private final int port;

}
```

文字列コンバータ

```java
public class HostPortConverter implements IStringConverter<HostPort> {

    @Override
    public HostPort convert(String value) {
        List<String> strList = List.of(value.split(":"));
        return new HostPort(strList.get(0), Integer.parseInt(strList.get(1)));
    }

}
```

Factory

```java
public class Factory implements IStringConverterFactory {

    @Override
    public Class<? extends IStringConverter<?>> getConverter(Class forType) {
        if (forType.equals(HostPort.class)) {
            return HostPortConverter.class;
        }
        return null;
    }

}
```

パラメータクラス

```java
public class CommandParameters {

    @Parameter(names = "-hostport")
    private HostPort hostPort;

}
```

Factory の使い方は以下です。

```java
CommandParameters parameters = new CommandParameters();
JCommander commander = JCommander.newBuilder()
        .addObject(parameters)
        .addConverterFactory(new Factory())
        .build();
commander.usage();

String[] argv = {"-hostport", "example.com:8080"};
commander.parse(argv);
```

## 3.2 カスタムタイプ（リスト値）

`@Parameter` の `listConverter` 属性を使用し、`IStringConverter` を実装したクラスを割り当てることで、文字列を値のリストに変換します。

com.example.chapter03.section02

```java
public class FileListConverter implements IStringConverter<List<File>> {

    @Override
    public List<File> convert(String value) {
        List<String> strList = List.of(value.split(","));
        return strList.stream()
                .map(File::new)
                .collect(Collectors.toList());
    }

}
```

```java
public class CommandParameters {

    @Parameter(names = "-files", listConverter = FileListConverter.class)
    private List<File> files;

}
```

```
Usage: <main class> [options]
  Options:
    -files

[-files, file1,file2,file3]
CommandParameters(files=[file1, file2, file3])
```

### 3.3 分割

`@Parameter` の `splitter` 属性を使用し、`IParameterSplitter` を実装したクラスを割り当てることで、パラメーターがサブパーツに分割されます。

```java
public interface IParameterSplitter {
    List<String> split(String value);
}
```

com.example.chapter03.section03

```java
public class SemiColonSplitter implements IParameterSplitter {

    @Override
    public List<String> split(String value) {
        return List.of(value.split(";"));
    }

}
```

```java
Usage: <main class> [options]
  Options:
    -files


[-files, file1;file2;file3]
CommandParameters(files=[file1, file2, file3])
```

## 4. パラメータの検証

`IParameterValidator` を実装するクラスを提供することで、パラメータの検証ができます。

パラメータが正の整数であることを確認する実装例を示します。正の整数であることを検証するバリデータは、すでに実装されています。

```java
public class PositiveInteger implements IParameterValidator {

    public void validate(String name, String value) throws ParameterException {
        int n = Integer.parseInt(value);
        if (n < 0) {
            throw new ParameterException("Parameter " + name + " should be positive (found " + value +")");
        }
    }

}
```

com.example.chapter04.section01

```java
public class CommandParameters {

    @Parameter(names = "-age", validateWith = PositiveInteger.class)
    private int age;

}
```

```
[-age, -1]
Error: Parameter -age should be positive (found -1)
[-age, 1]
CommandParameters(age=1)
```

複数バリデータも以下のように指定できます。

```java
@Parameter(names = "-count", validateWith = {PositiveInteger.class, CustomOddNumberValidator.class})
private int value;
```

## 5. 主なパラメータ

`names` 属性を指定しないこともできます。しかし、指定しないパラメータは最大で1つです。また、指定を省略できるのは `List<String>` か `String` かコンバーターを持つタイプのいずれかです。

com.example.chapter05

```java
public class CommandParameters {

    @Parameter(description = "Files")
    private List<String> files = new ArrayList<>();

    @Parameter(names = "-debug", description = "Debugging level")
    private boolean debug = false;

}
```

```
Usage: <main class> [options] Files
  Options:
    -debug
      Debugging level
      Default: false

[-debug, file1, file2]
CommandParameters(files=[file1, file2], debug=true)
```

## 6. プライベートパラメータ

パラメータは プライベートにすることもできます。

## 7. パラメータセパレータ

デフォルトでは、パラメータはスペース区切りですが、この設定を変更して区切り文字を任意で変更できるようになります。例えば、`-level=42` を解析できるようにするには以下のように実装します。

com.example.chapter07

```java
@Parameters(separators = "=")
public class CommandParameters {

    @Parameter(names = "-level")
    private int level = 2;

}
```

```
Usage: <main class> [options]
  Options:
    -level
      Default: 2

[-level=42]
CommandParameters(level=42)
```

## 8. 複数の説明

パラメータの説明を複数のクラスで実装することができます。

com.example.chapter08

```java
public class MasterParameter {

    @Parameter(names = "-master")
    private String master;

}
```

```java
public class SlaveParameter {

    @Parameter(names = "-slave")
    private String slave;

}
```

```java
MasterParameter masterParam = new MasterParameter();
SlaveParameter slaveParam = new SlaveParameter();
JCommander commander = JCommander.newBuilder()
        .addObject(new Object[]{masterParam, slaveParam})
        .build();
commander.usage();

String[] argv = {"-master", "master", "-slave", "slave"};
commander.parse(argv);
```

```
Usage: <main class> [options]
  Options:
    -master
    -slave

[-master, master, -slave, slave]
MasterParameter(master=master)
SlaveParameter(slave=slave)
```

## 10. パラメータの複数の値

### 10.1 値の数が固定

パラメータの数を制限したい場合は `arity = 2` と指定すると制限できます。

com.example.chapter10.section01

```java
public class CommandParameters {

    @Parameter(names = "-pairs", arity = 2, description = "Pairs")
    private List<String> pairs;

}
```

```
Usage: <main class> [options]
  Options:
    -pairs
      Pairs

[-pairs, slave]
Error: Expected 2 values after -pairs
[-pairs, slave, master]
CommandParameters(pairs=[slave, master])
[-pairs, slave, master, foo.xml]
Error: Was passed main parameter 'foo.xml' but no main parameter was defined in your arg class
```

`boolean`, `boolean` はデフォルトが 0 で、`String`, `Integer`, `int`, `Long`, `long` はデフォルトが 1 なので、指定する必要はありません。

また、アリティを定義するパラメータには `List<String>` のみで指定できます。リストの型が整数型またはその他の型である場合は、これらの値を自分で変換する必要があります。

### 10.2 値の数が可変

パラメータの数が不明な場合、パラメータは `List<String>` で受け取る必要があり、`variableArity` を `true` に設定します。

com.example.chapter10.section02

```java
public class CommandParameters {

    @Parameter(names = "-foo", variableArity = true)
    public List<String> foo = new ArrayList<>();

    @Parameter(names = "-bar")
    public boolean bar = false;

}
```

```
Usage: <main class> [options]
  Options:
    -bar
      Default: false
    -foo
      Default: []

[-foo, a1, -bar]
CommandParameters(foo=[a1], bar=true)
[-foo, a1, a2, -bar]
CommandParameters(foo=[a1, a2], bar=true)
```

## 11. 複数のオプション名

複数のオプション名を指定できます。

com.example.chapter11

```java
public class CommandParameters {

    @Parameter(names = {"-d", "--outputDirectory"}, description = "Directory")
    private String outputDirectory;

}
```

```
Usage: <main class> [options]
  Options:
    -d, --outputDirectory
      Directory

[-d, /tmp]
CommandParameters(outputDirectory=/tmp)
[--outputDirectory, /tmp]
CommandParameters(outputDirectory=/tmp)
```

## 12. その他のオプション構成

`JCommander#setCaseSensitiveOptions(boolean)` で `false` を渡すと、オプションの大文字小文字を区別しなくなります。デフォルトは `true` です。

com.example.chapter12.sample01

```java
public class CommandParameters {

    @Parameter(names = "-name")
    private String name;

}
```

```java
CommandParameters parameters = new CommandParameters();
JCommander commander = JCommander.newBuilder()
        .addObject(parameters)
        .build();
commander.setCaseSensitiveOptions(false);
```

```
Usage: <main class> [options]
  Options:
    -name

[-NAME, name]
CommandParameters(name=name)
```

`JCommander#setAllowAbbreviatedOptions(boolean)` で `true` を渡すと、ユーザーがオプションを省略して指定しても理解してくれます。デフォルトは `false` です。

com.example.chapter12.sample02

```java
public class CommandParameters {

    @Parameter(names = "-param")
    private String param;

}
```

```java
CommandParameters parameters = new CommandParameters();
JCommander commander = JCommander.newBuilder()
        .addObject(parameters)
        .build();
commander.setAllowAbbreviatedOptions(true);
```

```
Usage: <main class> [options]
  Options:
    -param

[-pa, param]
CommandParameters(param=param)
```

## 13. 必須オプション

`required` 属性を `true` にすることで必須パラメータにすることができます。デフォルトは `false` です。

com.example.chapter13

```java
public class CommandParameters {

    @Parameter(names = "-host", required = true)
    private String host;

}
```

```
Usage: <main class> [options]
  Options:
  * -host

[]
Error: The following option is required: [-host]
```

## 14. デフォルト値

パラメータのデフォルト値を指定する一番簡単な方法は宣言時にフィールドを初期化することです。

com.example.chapter14.sample01

```java
public class CommandParameters {

    @Parameter(names = "-level")
    private int logLevel = 3;

}
```

```
Usage: <main class> [options]
  Options:
    -level
      Default: 3

[]
CommandParameters(logLevel=3)
[-level, 5]
CommandParameters(logLevel=5)
```

複数のパラメータで同一のデフォルト値が利用できるようにするには、`IDefaultProvider` クラスを使用して設定できます。

```java
public interface IDefaultProvider {
    String getDefaultValueFor(String optionName);
}
```

com.example.chapter14.sample02

```java
public class CommandParameters {

    @Parameter(names = "-debug")
    private String debug;

    @Parameter(names = "-num1")
    private int num1;

    @Parameter(names = "-num2")
    private int num2;

}
```

```java
private static final IDefaultProvider DEFAULT_PROVIDER = new IDefaultProvider() {
    @Override
    public String getDefaultValueFor(String optionName) {
        return "-debug".equals(optionName) ? "false" : "42";
    }
};

public static void main(String[] args) {
    CommandParameters parameters = new CommandParameters();
    JCommander commander = JCommander.newBuilder()
            .addObject(parameters)
            .defaultProvider(DEFAULT_PROVIDER)
            .build();
}
```

```
Usage: <main class> [options]
  Options:
    -debug
    -num1
    -num2

[]
CommandParameters(debug=false, num1=42, num2=42)
```

## 15. ヘルプパラメータ

パラメータの1つを使用してヘルプまたは使用法を表示する場合は、help属性を使用する必要があります。

```java
public class CommandParameters {

    @Parameter(names = "--name")
    private String name;

    @Parameter(names = "--help", help = true)
    private boolean help;

}
```

```
Usage: <main class> [options]
  Options:
    --help
      Default: false
    --name

[]
CommandParameters(name=null, help=false)
[--help]
CommandParameters(name=null, help=true)
```

## 16. コマンド登録

```
$ git commit --amend -m "Bug fix"
```

`commit` はJCommanderではコマンドと呼ばれ、コマンドごとにargオブジェクトを作成することができます。

com.example.chapter16

```java
public class CommandParameters {

    @Parameter(names = "-v")
    private boolean verbose;

}
```

```java
@Parameters(separators = "=", commandDescription = "Record changes to the repository")
public class CommandCommit {

    @Parameter(description = "The list of files to commit")
    private List<String> files;

    @Parameter(names = "--amend", description = "Amend")
    private boolean amend = false;

    @Parameter(names = "--author")
    private String author;

}
```

```java
@Parameters(commandDescription = "Add file contents to the index")
public class CommandAdd {

    @Parameter(description = "File patterns to add to the index")
    private List<String> patterns;

    @Parameter(names = "-i")
    private boolean interactive = false;

}
```

```java
CommandParameters parameters = new CommandParameters();
CommandAdd add = new CommandAdd();
CommandCommit commit = new CommandCommit();
JCommander commander = JCommander.newBuilder()
        .addObject(parameters)
        .addCommand("add", add)
        .addCommand("commit", commit)
        .build();
```

```
Usage: <main class> [options] [command] [command options]
  Options:
    -v
      Default: false
  Commands:
    add      Add file contents to the index
      Usage: add [options] File patterns to add to the index
        Options:
          -i
            Default: false

    commit      Record changes to the repository
      Usage: commit [options] The list of files to commit
        Options:
          --amend
            Amend
            Default: false
          --author

[-v, commit, --amend, --author=cbeust, A.class, B.class]
CommandParameters(verbose=true)
CommandAdd(patterns=null, interactive=false)
CommandCommit(files=[A.class, B.class], amend=true, author=cbeust)
```

## 17. 例外

JCommanderがエラーを検出すると、`ParameterException` をスローします。

## 18. usage

`usage()` を呼び出すことでUSAGEを出力することができます。アスタリスクが前についているオプションは必須です。`order` 属性で表示順を指定できます。

com.example.chapter18

```java
public class CommandParameters {

    @Parameter(names = "-name", description = "your name", required = true, order = 1)
    private String name;

    @Parameter(names = "-age", description = "your age", order = 2)
    private int age;

}
```

```java
CommandParameters parameters = new CommandParameters();
JCommander commander = JCommander.newBuilder()
        .addObject(parameters)
        .build();
commander.setProgramName("program_name");
commander.usage();
```

```
Usage: program_name [options]
  Options:
  * -name
      your name
    -age
      your age
      Default: 0
```

## 19. usageでパラメータを非表示にする

非表示にするには `hidden` 属性を `true` にします。

com.example.chapter19

```java
public class CommandParameters {

    @Parameter(names = "-name", description = "your name")
    private String name;

    @Parameter(names = "-debug", description = "Debug mode", hidden = true)
    private boolean debug = false;

}
```

```
Usage: <main class> [options]
  Options:
    -name
      your name
```

## 20. 翻訳

メッセージバンドルで名前を定義し、`descriptionKey` 属性でメッセージバンドルのキーを指定することで `description` を翻訳後の文章を表示します。

com.example.chapter20

```java
@Parameters(resourceBundle = "MessageBundle")
public class CommandParameters {

    @Parameter(names = "-host", description = "Host", descriptionKey = "host")
    private String host;

}
```

```properties
host=ホスト
```

```
Usage: <main class> [options]
  Options:
    -host
      ホスト
```

## 21. オプションを共通で使えるようにする方法

共通パラメータのクラスを定義して、`@ParameterDelegate` を宣言に追加することで、共通パラメータを追加できます。

com.example.chapter21

```java
public class Delegate {

    @Parameter(names = "-port")
    private int port;

}
```

```java
public class CommandParameters {

    @Parameter(names = "-v")
    private boolean verbose;

    @ParametersDelegate
    private Delegate delegate = new Delegate();

}
```

```java
Usage: <main class> [options]
  Options:
    -port
      Default: 0
    -v
      Default: false

[-v, -port, 1234]
CommandParameters(verbose=true, delegate=Delegate(port=1234))
```

## 22. 動的パラメータ

com.example.chapter22

```java
public class CommandParameters {

    @DynamicParameter(names = "-D", description = "Dynamic parameters go here")
    private Map<String, String> params = new HashMap<>();

}
```

```
Usage: <main class> [options]
  Options:
    -D
      Dynamic parameters go here
      Syntax: -Dkey=value
      Default: {}

[-Da=b, -Dc=d]
CommandParameters(params={a=b, c=d})
```
