
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



public interface UpdateDeliveryMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      UpdateDeliveryMutation.Data,
      UpdateDeliveryMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val id: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val delivery_update: DeliveryKey?,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "UpdateDelivery"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun UpdateDeliveryMutation.ref(
  
    id: java.util.UUID,

  
  
): com.google.firebase.dataconnect.MutationRef<
    UpdateDeliveryMutation.Data,
    UpdateDeliveryMutation.Variables
  > =
  ref(
    
      UpdateDeliveryMutation.Variables(
        id=id,
  
      )
    
  )

public suspend fun UpdateDeliveryMutation.execute(

  
    
      id: java.util.UUID,

  

  ): com.google.firebase.dataconnect.MutationResult<
    UpdateDeliveryMutation.Data,
    UpdateDeliveryMutation.Variables
  > =
  ref(
    
      id=id,
  
    
  ).execute()


