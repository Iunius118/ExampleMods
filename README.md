# ExampleMods 1.14.4

Example Mods which are compatible with Minecraft Forge 1.14.4-28.2.0 MDK

- **exampleblock**: ブロックの追加
- **exampleconfig**: コンフィグの追加。コンフィグファイルはTOML形式で`config/exampleconfig-common.toml`及び`[world_dir]/serverconfig/exampleconfig-server.toml`、`config/exampleconfig-client.toml`（クライアント側のみ）に保存される。これらはmodのロード時とコンフィグファイルの変更時に読み込まれる
- **exampledatagenerator**: データジェネレーターを利用した各種JSON（BlockState及びBlockモデル、Itemモデル、Lang、レシピ、レシピ進捗、Blockルートテーブル、タグ）の生成。生成は`runData`実行時に行われ、デフォルトでは`src/generated/resources`内に保存される。このディレクトリーを`build.gradle`でリソースディレクトリーに指定すれば生成されたJSONをコピペせずにmodから利用することができる
- **exampledistexecutor**: プロキシシステム（SidedProxy）、`DistExecutor`を使用した物理サーバー／物理クライアント限定の処理
- **examplefluid**: 流体と流体ブロック、流体入りバケツの追加。バニラの水流と同じく流体がエンティティーを流せるようにするため、modの追加流体をデータパックでバニラの`FluidTags.WATER`タグに追加している。また今のところForgeのバケツモデル追加システムが未実装なため、流体入りバケツのアイテムモデルとテクスチャーは自前で用意している
- **exampleitem**: アイテムとアイテムグループ（クリエイティブタブ）の追加
- **exampleitemcontainer**: 1スロットのインベントリを持つブロックとGUIの追加
- **examplemod**: 基本サンプルMOD
- **exampletileentity**: タイルエンティティーとタイルエンティティーレンダラーの追加
