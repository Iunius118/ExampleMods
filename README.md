# ExampleMods 1.14.4

Example Mods which are compatible with Minecraft Forge 1.14.4-28.2.0 MDK

## Modリスト

- **exampleblock**：ブロックの追加
- **exampleconfig**：コンフィグの追加。コンフィグファイルはTOML形式で保存される。  
コンフィグには以下の3タイプがある：
  - COMMON (`config/exampleconfig-common.toml`)：サーバーとクライアント両方の`config`ディレクトリー内に保存される。ゲーム実行中に変更しても反映される。サーバーとクライアントの間で同期しない
  - CLIENT (`config/exampleconfig-client.toml`)：クライアントの`config`ディレクトリー内に保存される。ゲーム実行中に変更しても反映される。サーバーとクライアントの間で同期しない
  - SERVER (`[world_dir]/serverconfig/exampleconfig-server.toml`)：セーブされた各ワールドディレクトリーの`serverconfig`ディレクトリー内に保存される。ゲームサーバー起動時のみ読み込まれる。サーバーへ接続した各クライアントに同期される
- **exampledatagenerator**：データジェネレーターを利用した各種JSONの生成。このExample Modsで使用されているすべてのJSONファイルを生成している。生成は`runData`実行時に行われ、デフォルトでは`src/generated/resources`内に保存される。このディレクトリーを`build.gradle`でリソースディレクトリーに指定すれば生成されたJSONをコピペせずにmodから利用することができる。  
このmodでは以下のJSONを生成している：
  - BlockState及びBlockモデル
  - Itemモデル
  - Langファイル
  - レシピ及びレシピブック進捗
  - ルートテーブル
  - タグ
- **exampledistexecutor**：プロキシシステム（SidedProxy）、`DistExecutor`を使用した物理サーバー／物理クライアント限定の処理
- **examplefluid**：流体と流体ブロック、流体入りバケツの追加。バニラの水流と同じく流体がエンティティーを流せるようにするため、データパックで流体をバニラの`FluidTags.WATER`タグに追加している。また今のところForgeのバケツモデル追加システムが未実装なため、流体入りバケツのアイテムモデルとテクスチャーは自前で用意している（追記：Forge 28.2.2でバケツモデルローダーが実装されたらしい）
- **exampleitem**：アイテムとアイテムグループ（クリエイティブタブ）の追加
- **exampleitemcontainer**：1スロットのインベントリを持つブロックとGUIの追加
- **examplemod**：基本サンプルMOD
- **exampletileentity**：タイルエンティティーとタイルエンティティーレンダラーの追加

## 注記

各modのmodイベント用の`@EventBusSubscriber`アノテーションで

```java
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
```

と指定しているが、`modid = MOD_ID,`部分はこのExampleModsが複数のmodで構成されているために指定しているものであり、単一modのコアクラス（`@Mod`アノテーションを付けたクラス）で使用する場合は不要である（単一modでもコアクラス以外で使用する場合は必要）。
