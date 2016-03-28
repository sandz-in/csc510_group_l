# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('expense_sharing', '0006_deleteaction'),
    ]

    operations = [
        migrations.AddField(
            model_name='deleteaction',
            name='billtype',
            field=models.CharField(max_length=128, null=True),
        ),
    ]
