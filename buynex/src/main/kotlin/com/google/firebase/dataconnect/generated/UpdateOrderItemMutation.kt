
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



public interface UpdateOrderItemMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      UpdateOrderItemMutation.Data,
      UpdateOrderItemMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val id: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val orderItem_update: OrderItemKey?,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "UpdateOrderItem"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun UpdateOrderItemMutation.ref(
  
    id: java.util.UUID,

  
  
): com.google.firebase.dataconnect.MutationRef<
    UpdateOrderItemMutation.Data,
    UpdateOrderItemMutation.Variables
  > =
  ref(
    
      UpdateOrderItemMutation.Variables(
        id=id,
  
      )
    
  )

public suspend fun UpdateOrderItemMutation.execute(

  
    
      id: java.util.UUID,

  

  ): com.google.firebase.dataconnect.MutationResult<
    UpdateOrderItemMutation.Data,
    UpdateOrderItemMutation.Variables
  > =
  ref(
    
      id=id,
  
    
  ).execute()


