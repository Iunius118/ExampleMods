# ExampleMods 1.14.4

Example Mods which are compatible with Minecraft Forge 1.14.4-28.1.0 MDK

- **exampleblock**: ブロックの追加
- **exampleconfig**: コンフィグの追加。コンフィグファイルはTOML形式で`config/exampleconfig-common.toml`及び`[world_dir]/serverconfig/exampleconfig-server.toml`、`config/exampleconfig-client.toml`（クライアント側のみ）に保存され、modのロード時とコンフィグファイルの変更時に読み込まれる
- **exampledatagenerator**: データジェネレーターを利用したクラフト・精錬レシピJSON（＋レシピブック追加進捗JSON）の生成。生成は`runData`実行／デバッグ時に行われ、デフォルトでは`src/generated/resources`内に保存される。このディレクトリーを`build.gradle`でリソースのソースディレクトリーに指定することで、生成されたJSONをコピペせずにmodから利用することも可能
- **exampledistexecutor**: プロキシシステム（SidedProxy）、`DistExecutor`を使用した物理サーバー／物理クライアント限定の処理
- **examplefluid**: 流体と流体ブロック、流体入りバケツの追加。流体がバニラの水流と同じくエンティティーを押し流せるようにするため、データパックでバニラの`FluidTags.WATER`に追加している。また今のところForgeのバケツモデル追加システムが未実装なため、流体入りバケツのアイテムモデルとテクスチャーは自前で用意している
- **exampleitem**: アイテムとアイテムグループ（クリエイティブタブ）の追加
- **examplemod**: 基本サンプルMOD
- **exampletileentity**: タイルエンティティーとタイルエンティティーレンダラーの追加
