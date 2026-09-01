
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



public interface DeleteUserMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      DeleteUserMutation.Data,
      Unit
    >
{
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val user_delete: UserKey?,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "DeleteUser"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Unit> =
      kotlinx.serialization.serializer()
  }
}

public fun DeleteUserMutation.ref(
  
): com.google.firebase.dataconnect.MutationRef<
    DeleteUserMutation.Data,
    Unit
  > =
  ref(
    
      Unit
    
  )

public suspend fun DeleteUserMutation.execute(

  

  ): com.google.firebase.dataconnect.MutationResult<
    DeleteUserMutation.Data,
    Unit
  > =
  ref(
    
  ).execute()


