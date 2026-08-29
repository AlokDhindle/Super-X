
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



public interface UpdateUserMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      UpdateUserMutation.Data,
      Unit
    >
{
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val user_update: UserKey?,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "UpdateUser"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Unit> =
      kotlinx.serialization.serializer()
  }
}

public fun UpdateUserMutation.ref(
  
): com.google.firebase.dataconnect.MutationRef<
    UpdateUserMutation.Data,
    Unit
  > =
  ref(
    
      Unit
    
  )

public suspend fun UpdateUserMutation.execute(

  

  ): com.google.firebase.dataconnect.MutationResult<
    UpdateUserMutation.Data,
    Unit
  > =
  ref(
    
  ).execute()


