def send_notification(mail_subject, mail_body, recipients="rhv-qe-devops", reply_to="rhv-qe-devops"){
  mail subject: mail_subject, \
    to: recipients, \
    replyTo: reply_to, \
    body: """${mail_body}

Thanks in advance,
  rhv-qe-devops team"""
}


def failed_job_notification(failed_build, failed_flow, builder_user="Unknown"){
  def failed_stage = other.parse_url(failed_build)
  def team_email = other.get_team_mailing_list(failed_build)
//   def recipient = team_email.contains("rhv-qe-devops")? team_email : "${team_email},rhv-qe-devops"
  def recipient = "khakimi"
  def subject= "[Action Required]: ${failed_stage} has FAILED! (builder user: ${builder_user})"
  def body = """Hi,
The following job (built by ${builder_user}):
${failed_build}consoleFull
has FAILED in stage:
${failed_flow}consoleFull
Please investigate the root cause of the job failure.

In case it’s infra related issue, please contact rhv-qe-devops team."""
  send_notification(subject, body, recipient)
}
