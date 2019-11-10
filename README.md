# ExampleMods 1.14.4

Example Mods which are compatible with Minecraft Forge 1.14.4-28.1.0 MDK

- **exampleblock**: ブロックの追加
- **exampleconfig**: コンフィグの追加。コンフィグファイルはTOML形式で`config`フォルダ内の`exampleconfig-common.toml`（サーバー・クライアント共通）及び、`exampleconfig-server.toml`（サーバー側のみ）または`exampleconfig-client.toml`（クライアント側のみ）に保存され、modロード時とコンフィグファイル変更時に読み込まれる
- **exampledatagenerator**: データジェネレーターを利用したクラフト・精錬レシピJSON（＋レシピブック追加進捗JSON）の生成。生成は`runData`時に行われ、デフォルトでは`src/generated`内に生成される。生成先のディレクトリーを`build.gradle`でリソースのソースディレクトリーに指定することで、生成されたJSONをコピペせずに利用することも可能
- **exampledistexecutor**: プロキシシステム、`DistExecutor`を使用した物理サーバー／物理クライアント限定の処理
- **exampleitem**: アイテムとアイテムグループ（クリエイティブタブ）の追加
- **examplemod**: 基本サンプルMOD
- **exampletileentity**: タイルエンティティーとタイルエンティティーレンダラーの追加
