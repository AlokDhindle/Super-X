
@file:Suppress(
  "KotlinRedundantDiagnosticSuppress",
  "PropertyName",
  "MayBeConstant",
  "RedundantVisibilityModifier",
  "RedundantCompanionReference",
  "RemoveEmptyClassBody",
  "SpellCheckingInspection",
  "unused",
)

package com.google.firebase.dataconnect.generated

import com.google.firebase.dataconnect.getInstance as _fdcGetInstance
import kotlin.time.Duration.Companion.milliseconds as _milliseconds

public interface ExampleConnector : com.google.firebase.dataconnect.generated.GeneratedConnector<ExampleConnector> {
  override val dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect

  
    public val createDelivery: CreateDeliveryMutation
  
    public val createOrder: CreateOrderMutation
  
    public val createOrderItem: CreateOrderItemMutation
  
    public val createProduct: CreateProductMutation
  
    public val createStore: CreateStoreMutation
  
    public val createUser: CreateUserMutation
  
    public val deleteDelivery: DeleteDeliveryMutation
  
    public val deleteOrder: DeleteOrderMutation
  
    public val deleteOrderItem: DeleteOrderItemMutation
  
    public val deleteProduct: DeleteProductMutation
  
    public val deleteStore: DeleteStoreMutation
  
    public val deleteUser: DeleteUserMutation
  
    public val getDelivery: GetDeliveryQuery
  
    public val getOrder: GetOrderQuery
  
    public val getOrderItem: GetOrderItemQuery
  
    public val getProduct: GetProductQuery
  
    public val getStore: GetStoreQuery
  
    public val getUser: GetUserQuery
  
    public val listDeliveries: ListDeliveriesQuery
  
    public val listMyOrders: ListMyOrdersQuery
  
    public val listOrderItems: ListOrderItemsQuery
  
    public val listProducts: ListProductsQuery
  
    public val listStores: ListStoresQuery
  
    public val listUsers: ListUsersQuery
  
    public val updateDelivery: UpdateDeliveryMutation
  
    public val updateOrder: UpdateOrderMutation
  
    public val updateOrderItem: UpdateOrderItemMutation
  
    public val updateProduct: UpdateProductMutation
  
    public val updateStore: UpdateStoreMutation
  
    public val updateUser: UpdateUserMutation
  

  public companion object {
    @Suppress("MemberVisibilityCanBePrivate")
    public val config: com.google.firebase.dataconnect.ConnectorConfig = com.google.firebase.dataconnect.ConnectorConfig(
      connector = "example",
      location = "us-east4",
      serviceId = "super-x",
    )

    public fun getInstance(
      dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect
    ):ExampleConnector = synchronized(instances) {
      instances.getOrPut(dataConnect) {
        ExampleConnectorImpl(dataConnect)
      }
    }

    private val instances = java.util.WeakHashMap<com.google.firebase.dataconnect.FirebaseDataConnect, ExampleConnectorImpl>()

    
    public val defaultCacheSettings: com.google.firebase.dataconnect.CacheSettings =
      com.google.firebase.dataconnect.CacheSettings(
        
        
      )

    public val defaultDataConnectSettings: com.google.firebase.dataconnect.DataConnectSettings =
      com.google.firebase.dataconnect.DataConnectSettings(
        cacheSettings = defaultCacheSettings,
      )
    
  }
}

public val ExampleConnector.Companion.instance:ExampleConnector
  get() = getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(
    config, defaultDataConnectSettings
  ))

public fun ExampleConnector.Companion.getInstance(
  settings: com.google.firebase.dataconnect.DataConnectSettings = defaultDataConnectSettings
):ExampleConnector =
  getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(config, settings))

public fun ExampleConnector.Companion.getInstance(
  app: com.google.firebase.FirebaseApp,
  settings: com.google.firebase.dataconnect.DataConnectSettings = defaultDataConnectSettings
):ExampleConnector =
  getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(app, config, settings))

private class ExampleConnectorImpl(
  override val dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect
) : ExampleConnector {
  
    override val createDelivery by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CreateDeliveryMutationImpl(this)
    }
  
    override val createOrder by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CreateOrderMutationImpl(this)
    }
  
    override val createOrderItem by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CreateOrderItemMutationImpl(this)
    }
  
    override val createProduct by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CreateProductMutationImpl(this)
    }
  
    override val createStore by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CreateStoreMutationImpl(this)
    }
  
    override val createUser by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CreateUserMutationImpl(this)
    }
  
    override val deleteDelivery by lazy(LazyThreadSafetyMode.PUBLICATION) {
      DeleteDeliveryMutationImpl(this)
    }
  
    override val deleteOrder by lazy(LazyThreadSafetyMode.PUBLICATION) {
      DeleteOrderMutationImpl(this)
    }
  
    override val deleteOrderItem by lazy(LazyThreadSafetyMode.PUBLICATION) {
      DeleteOrderItemMutationImpl(this)
    }
  
    override val deleteProduct by lazy(LazyThreadSafetyMode.PUBLICATION) {
      DeleteProductMutationImpl(this)
    }
  
    override val deleteStore by lazy(LazyThreadSafetyMode.PUBLICATION) {
      DeleteStoreMutationImpl(this)
    }
  
    override val deleteUser by lazy(LazyThreadSafetyMode.PUBLICATION) {
      DeleteUserMutationImpl(this)
    }
  
    override val getDelivery by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetDeliveryQueryImpl(this)
    }
  
    override val getOrder by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetOrderQueryImpl(this)
    }
  
    override val getOrderItem by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetOrderItemQueryImpl(this)
    }
  
    override val getProduct by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetProductQueryImpl(this)
    }
  
    override val getStore by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetStoreQueryImpl(this)
    }
  
    override val getUser by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetUserQueryImpl(this)
    }
  
    override val listDeliveries by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListDeliveriesQueryImpl(this)
    }
  
    override val listMyOrders by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListMyOrdersQueryImpl(this)
    }
  
    override val listOrderItems by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListOrderItemsQueryImpl(this)
    }
  
    override val listProducts by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListProductsQueryImpl(this)
    }
  
    override val listStores by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListStoresQueryImpl(this)
    }
  
    override val listUsers by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListUsersQueryImpl(this)
    }
  
    override val updateDelivery by lazy(LazyThreadSafetyMode.PUBLICATION) {
      UpdateDeliveryMutationImpl(this)
    }
  
    override val updateOrder by lazy(LazyThreadSafetyMode.PUBLICATION) {
      UpdateOrderMutationImpl(this)
    }
  
    override val updateOrderItem by lazy(LazyThreadSafetyMode.PUBLICATION) {
      UpdateOrderItemMutationImpl(this)
    }
  
    override val updateProduct by lazy(LazyThreadSafetyMode.PUBLICATION) {
      UpdateProductMutationImpl(this)
    }
  
    override val updateStore by lazy(LazyThreadSafetyMode.PUBLICATION) {
      UpdateStoreMutationImpl(this)
    }
  
    override val updateUser by lazy(LazyThreadSafetyMode.PUBLICATION) {
      UpdateUserMutationImpl(this)
    }
  

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun operations(): List<com.google.firebase.dataconnect.generated.GeneratedOperation<ExampleConnector, *, *>> =
    queries() + mutations()

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun mutations(): List<com.google.firebase.dataconnect.generated.GeneratedMutation<ExampleConnector, *, *>> =
    listOf(
      createDelivery,
        createOrder,
        createOrderItem,
        createProduct,
        createStore,
        createUser,
        deleteDelivery,
        deleteOrder,
        deleteOrderItem,
        deleteProduct,
        deleteStore,
        deleteUser,
        updateDelivery,
        updateOrder,
        updateOrderItem,
        updateProduct,
        updateStore,
        updateUser,
        
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun queries(): List<com.google.firebase.dataconnect.generated.GeneratedQuery<ExampleConnector, *, *>> =
    listOf(
      getDelivery,
        getOrder,
        getOrderItem,
        getProduct,
        getStore,
        getUser,
        listDeliveries,
        listMyOrders,
        listOrderItems,
        listProducts,
        listStores,
        listUsers,
        
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect) =
    ExampleConnectorImpl(dataConnect)

  override fun equals(other: Any?): Boolean =
    other is ExampleConnectorImpl &&
    other.dataConnect == dataConnect

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "ExampleConnectorImpl",
      dataConnect,
    )

  override fun toString(): String =
    "ExampleConnectorImpl(dataConnect=$dataConnect)"
}



private open class ExampleConnectorGeneratedQueryImpl<Data, Variables>(
  override val connector: ExampleConnector,
  override val operationName: String,
  override val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
  override val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
) : com.google.firebase.dataconnect.generated.GeneratedQuery<ExampleConnector, Data, Variables> {

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(
    connector: ExampleConnector,
    operationName: String,
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
    variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
  ) =
    ExampleConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewVariables> withVariablesSerializer(
    variablesSerializer: kotlinx.serialization.SerializationStrategy<NewVariables>
  ) =
    ExampleConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewData> withDataDeserializer(
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<NewData>
  ) =
    ExampleConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun equals(other: Any?): Boolean =
    other is ExampleConnectorGeneratedQueryImpl<*,*> &&
    other.connector == connector &&
    other.operationName == operationName &&
    other.dataDeserializer == dataDeserializer &&
    other.variablesSerializer == variablesSerializer

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "ExampleConnectorGeneratedQueryImpl",
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun toString(): String =
    "ExampleConnectorGeneratedQueryImpl(" +
    "operationName=$operationName, " +
    "dataDeserializer=$dataDeserializer, " +
    "variablesSerializer=$variablesSerializer, " +
    "connector=$connector)"
}

private open class ExampleConnectorGeneratedMutationImpl<Data, Variables>(
  override val connector: ExampleConnector,
  override val operationName: String,
  override val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
  override val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
) : com.google.firebase.dataconnect.generated.GeneratedMutation<ExampleConnector, Data, Variables> {

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(
    connector: ExampleConnector,
    operationName: String,
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
    variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
  ) =
    ExampleConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewVariables> withVariablesSerializer(
    variablesSerializer: kotlinx.serialization.SerializationStrategy<NewVariables>
  ) =
    ExampleConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewData> withDataDeserializer(
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<NewData>
  ) =
    ExampleConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun equals(other: Any?): Boolean =
    other is ExampleConnectorGeneratedMutationImpl<*,*> &&
    other.connector == connector &&
    other.operationName == operationName &&
    other.dataDeserializer == dataDeserializer &&
    other.variablesSerializer == variablesSerializer

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "ExampleConnectorGeneratedMutationImpl",
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun toString(): String =
    "ExampleConnectorGeneratedMutationImpl(" +
    "operationName=$operationName, " +
    "dataDeserializer=$dataDeserializer, " +
    "variablesSerializer=$variablesSerializer, " +
    "connector=$connector)"
}



private class CreateDeliveryMutationImpl(
  connector: ExampleConnector
):
  CreateDeliveryMutation,
  ExampleConnectorGeneratedMutationImpl<
      CreateDeliveryMutation.Data,
      CreateDeliveryMutation.Variables
  >(
    connector,
    CreateDeliveryMutation.Companion.operationName,
    CreateDeliveryMutation.Companion.dataDeserializer,
    CreateDeliveryMutation.Companion.variablesSerializer,
  )


private class CreateOrderMutationImpl(
  connector: ExampleConnector
):
  CreateOrderMutation,
  ExampleConnectorGeneratedMutationImpl<
      CreateOrderMutation.Data,
      CreateOrderMutation.Variables
  >(
    connector,
    CreateOrderMutation.Companion.operationName,
    CreateOrderMutation.Companion.dataDeserializer,
    CreateOrderMutation.Companion.variablesSerializer,
  )


private class CreateOrderItemMutationImpl(
  connector: ExampleConnector
):
  CreateOrderItemMutation,
  ExampleConnectorGeneratedMutationImpl<
      CreateOrderItemMutation.Data,
      CreateOrderItemMutation.Variables
  >(
    connector,
    CreateOrderItemMutation.Companion.operationName,
    CreateOrderItemMutation.Companion.dataDeserializer,
    CreateOrderItemMutation.Companion.variablesSerializer,
  )


private class CreateProductMutationImpl(
  connector: ExampleConnector
):
  CreateProductMutation,
  ExampleConnectorGeneratedMutationImpl<
      CreateProductMutation.Data,
      CreateProductMutation.Variables
  >(
    connector,
    CreateProductMutation.Companion.operationName,
    CreateProductMutation.Companion.dataDeserializer,
    CreateProductMutation.Companion.variablesSerializer,
  )


private class CreateStoreMutationImpl(
  connector: ExampleConnector
):
  CreateStoreMutation,
  ExampleConnectorGeneratedMutationImpl<
      CreateStoreMutation.Data,
      Unit
  >(
    connector,
    CreateStoreMutation.Companion.operationName,
    CreateStoreMutation.Companion.dataDeserializer,
    CreateStoreMutation.Companion.variablesSerializer,
  )


private class CreateUserMutationImpl(
  connector: ExampleConnector
):
  CreateUserMutation,
  ExampleConnectorGeneratedMutationImpl<
      CreateUserMutation.Data,
      Unit
  >(
    connector,
    CreateUserMutation.Companion.operationName,
    CreateUserMutation.Companion.dataDeserializer,
    CreateUserMutation.Companion.variablesSerializer,
  )


private class DeleteDeliveryMutationImpl(
  connector: ExampleConnector
):
  DeleteDeliveryMutation,
  ExampleConnectorGeneratedMutationImpl<
      DeleteDeliveryMutation.Data,
      DeleteDeliveryMutation.Variables
  >(
    connector,
    DeleteDeliveryMutation.Companion.operationName,
    DeleteDeliveryMutation.Companion.dataDeserializer,
    DeleteDeliveryMutation.Companion.variablesSerializer,
  )


private class DeleteOrderMutationImpl(
  connector: ExampleConnector
):
  DeleteOrderMutation,
  ExampleConnectorGeneratedMutationImpl<
      DeleteOrderMutation.Data,
      DeleteOrderMutation.Variables
  >(
    connector,
    DeleteOrderMutation.Companion.operationName,
    DeleteOrderMutation.Companion.dataDeserializer,
    DeleteOrderMutation.Companion.variablesSerializer,
  )


private class DeleteOrderItemMutationImpl(
  connector: ExampleConnector
):
  DeleteOrderItemMutation,
  ExampleConnectorGeneratedMutationImpl<
      DeleteOrderItemMutation.Data,
      DeleteOrderItemMutation.Variables
  >(
    connector,
    DeleteOrderItemMutation.Companion.operationName,
    DeleteOrderItemMutation.Companion.dataDeserializer,
    DeleteOrderItemMutation.Companion.variablesSerializer,
  )


private class DeleteProductMutationImpl(
  connector: ExampleConnector
):
  DeleteProductMutation,
  ExampleConnectorGeneratedMutationImpl<
      DeleteProductMutation.Data,
      DeleteProductMutation.Variables
  >(
    connector,
    DeleteProductMutation.Companion.operationName,
    DeleteProductMutation.Companion.dataDeserializer,
    DeleteProductMutation.Companion.variablesSerializer,
  )


private class DeleteStoreMutationImpl(
  connector: ExampleConnector
):
  DeleteStoreMutation,
  ExampleConnectorGeneratedMutationImpl<
      DeleteStoreMutation.Data,
      DeleteStoreMutation.Variables
  >(
    connector,
    DeleteStoreMutation.Companion.operationName,
    DeleteStoreMutation.Companion.dataDeserializer,
    DeleteStoreMutation.Companion.variablesSerializer,
  )


private class DeleteUserMutationImpl(
  connector: ExampleConnector
):
  DeleteUserMutation,
  ExampleConnectorGeneratedMutationImpl<
      DeleteUserMutation.Data,
      Unit
  >(
    connector,
    DeleteUserMutation.Companion.operationName,
    DeleteUserMutation.Companion.dataDeserializer,
    DeleteUserMutation.Companion.variablesSerializer,
  )


private class GetDeliveryQueryImpl(
  connector: ExampleConnector
):
  GetDeliveryQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetDeliveryQuery.Data,
      GetDeliveryQuery.Variables
  >(
    connector,
    GetDeliveryQuery.Companion.operationName,
    GetDeliveryQuery.Companion.dataDeserializer,
    GetDeliveryQuery.Companion.variablesSerializer,
  )


private class GetOrderQueryImpl(
  connector: ExampleConnector
):
  GetOrderQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetOrderQuery.Data,
      GetOrderQuery.Variables
  >(
    connector,
    GetOrderQuery.Companion.operationName,
    GetOrderQuery.Companion.dataDeserializer,
    GetOrderQuery.Companion.variablesSerializer,
  )


private class GetOrderItemQueryImpl(
  connector: ExampleConnector
):
  GetOrderItemQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetOrderItemQuery.Data,
      GetOrderItemQuery.Variables
  >(
    connector,
    GetOrderItemQuery.Companion.operationName,
    GetOrderItemQuery.Companion.dataDeserializer,
    GetOrderItemQuery.Companion.variablesSerializer,
  )


private class GetProductQueryImpl(
  connector: ExampleConnector
):
  GetProductQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetProductQuery.Data,
      GetProductQuery.Variables
  >(
    connector,
    GetProductQuery.Companion.operationName,
    GetProductQuery.Companion.dataDeserializer,
    GetProductQuery.Companion.variablesSerializer,
  )


private class GetStoreQueryImpl(
  connector: ExampleConnector
):
  GetStoreQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetStoreQuery.Data,
      GetStoreQuery.Variables
  >(
    connector,
    GetStoreQuery.Companion.operationName,
    GetStoreQuery.Companion.dataDeserializer,
    GetStoreQuery.Companion.variablesSerializer,
  )


private class GetUserQueryImpl(
  connector: ExampleConnector
):
  GetUserQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetUserQuery.Data,
      Unit
  >(
    connector,
    GetUserQuery.Companion.operationName,
    GetUserQuery.Companion.dataDeserializer,
    GetUserQuery.Companion.variablesSerializer,
  )


private class ListDeliveriesQueryImpl(
  connector: ExampleConnector
):
  ListDeliveriesQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListDeliveriesQuery.Data,
      Unit
  >(
    connector,
    ListDeliveriesQuery.Companion.operationName,
    ListDeliveriesQuery.Companion.dataDeserializer,
    ListDeliveriesQuery.Companion.variablesSerializer,
  )


private class ListMyOrdersQueryImpl(
  connector: ExampleConnector
):
  ListMyOrdersQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListMyOrdersQuery.Data,
      Unit
  >(
    connector,
    ListMyOrdersQuery.Companion.operationName,
    ListMyOrdersQuery.Companion.dataDeserializer,
    ListMyOrdersQuery.Companion.variablesSerializer,
  )


private class ListOrderItemsQueryImpl(
  connector: ExampleConnector
):
  ListOrderItemsQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListOrderItemsQuery.Data,
      Unit
  >(
    connector,
    ListOrderItemsQuery.Companion.operationName,
    ListOrderItemsQuery.Companion.dataDeserializer,
    ListOrderItemsQuery.Companion.variablesSerializer,
  )


private class ListProductsQueryImpl(
  connector: ExampleConnector
):
  ListProductsQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListProductsQuery.Data,
      Unit
  >(
    connector,
    ListProductsQuery.Companion.operationName,
    ListProductsQuery.Companion.dataDeserializer,
    ListProductsQuery.Companion.variablesSerializer,
  )


private class ListStoresQueryImpl(
  connector: ExampleConnector
):
  ListStoresQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListStoresQuery.Data,
      Unit
  >(
    connector,
    ListStoresQuery.Companion.operationName,
    ListStoresQuery.Companion.dataDeserializer,
    ListStoresQuery.Companion.variablesSerializer,
  )


private class ListUsersQueryImpl(
  connector: ExampleConnector
):
  ListUsersQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListUsersQuery.Data,
      Unit
  >(
    connector,
    ListUsersQuery.Companion.operationName,
    ListUsersQuery.Companion.dataDeserializer,
    ListUsersQuery.Companion.variablesSerializer,
  )


private class UpdateDeliveryMutationImpl(
  connector: ExampleConnector
):
  UpdateDeliveryMutation,
  ExampleConnectorGeneratedMutationImpl<
      UpdateDeliveryMutation.Data,
      UpdateDeliveryMutation.Variables
  >(
    connector,
    UpdateDeliveryMutation.Companion.operationName,
    UpdateDeliveryMutation.Companion.dataDeserializer,
    UpdateDeliveryMutation.Companion.variablesSerializer,
  )


private class UpdateOrderMutationImpl(
  connector: ExampleConnector
):
  UpdateOrderMutation,
  ExampleConnectorGeneratedMutationImpl<
      UpdateOrderMutation.Data,
      UpdateOrderMutation.Variables
  >(
    connector,
    UpdateOrderMutation.Companion.operationName,
    UpdateOrderMutation.Companion.dataDeserializer,
    UpdateOrderMutation.Companion.variablesSerializer,
  )


private class UpdateOrderItemMutationImpl(
  connector: ExampleConnector
):
  UpdateOrderItemMutation,
  ExampleConnectorGeneratedMutationImpl<
      UpdateOrderItemMutation.Data,
      UpdateOrderItemMutation.Variables
  >(
    connector,
    UpdateOrderItemMutation.Companion.operationName,
    UpdateOrderItemMutation.Companion.dataDeserializer,
    UpdateOrderItemMutation.Companion.variablesSerializer,
  )


private class UpdateProductMutationImpl(
  connector: ExampleConnector
):
  UpdateProductMutation,
  ExampleConnectorGeneratedMutationImpl<
      UpdateProductMutation.Data,
      UpdateProductMutation.Variables
  >(
    connector,
    UpdateProductMutation.Companion.operationName,
    UpdateProductMutation.Companion.dataDeserializer,
    UpdateProductMutation.Companion.variablesSerializer,
  )


private class UpdateStoreMutationImpl(
  connector: ExampleConnector
):
  UpdateStoreMutation,
  ExampleConnectorGeneratedMutationImpl<
      UpdateStoreMutation.Data,
      UpdateStoreMutation.Variables
  >(
    connector,
    UpdateStoreMutation.Companion.operationName,
    UpdateStoreMutation.Companion.dataDeserializer,
    UpdateStoreMutation.Companion.variablesSerializer,
  )


private class UpdateUserMutationImpl(
  connector: ExampleConnector
):
  UpdateUserMutation,
  ExampleConnectorGeneratedMutationImpl<
      UpdateUserMutation.Data,
      Unit
  >(
    connector,
    UpdateUserMutation.Companion.operationName,
    UpdateUserMutation.Companion.dataDeserializer,
    UpdateUserMutation.Companion.variablesSerializer,
  )


